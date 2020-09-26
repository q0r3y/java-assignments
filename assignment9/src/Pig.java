import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Assignment9 Pig Class
 * Creates a Pig object
 *
 * @ q0r3y
 * @ 05.08.20
 */

public class Pig
{
    private Die die;
    private ArrayList<Player> players;
    private boolean gameOver;
    private boolean gameLoaded;
    private File gameSave;

    /**
     * Constructor for objects of Pig class
     */

    public Pig(int numOfPlayers) {
        gameSave = new File("pigSave");
        this.players = new ArrayList<>();
        this.die = new Die();
        this.gameOver = false;
        // Load previous game if one exists
        try {
            loadGame();
        } catch (FileNotFoundException e) {
            System.out.println(":: Game save not found ::");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(!gameLoaded) {
            newGame(numOfPlayers);
        }
        playGame();
        saveGame();
    }

    // Pig main method
    public static void main(String[] args) {
        new Pig(2);
    }

    // Main game play loop
    public void playGame(){
        while(!gameOver) {
            for (Player player : players) {
                System.out.println("\n-----" + player.getName() + "'s turn!-----\n");
                Scanner input = new Scanner(System.in);
                int currentPoints = player.getPoints();
                boolean rolling = true;
                int turnPoints = 0;
                int roll = rollDie();
                while (rolling) {
                    if(roll != 1) {
                        turnPoints += roll;
                        System.out.println("  Rolled: " + roll);
                        System.out.println("  Current turn points: " + turnPoints);
                        System.out.println("  Current total points: " + currentPoints);
                        boolean chosen = false;
                        while (!chosen) {
                            System.out.println("  Would you like to roll again?");
                            String choice = input.nextLine();
                            if (choice.equals("yes")) {
                                roll = rollDie();
                                chosen = true;
                            } else if (choice.equals("no")) {
                                rolling = false;
                                chosen = true;
                            } else {
                                System.out.println("I do not understand that command.");
                                chosen = false;
                            }
                        }
                    } else {
                        System.out.println("    You rolled a 1! You score nothing this turn.");
                        turnPoints = 0;
                        rolling = false;
                    }
                    player.setPoints(currentPoints + turnPoints);
                }
                // Check for win
                if(player.getPoints() >= 100) {
                    System.out.println(player.getName() + " has won the game!");
                    player.setWins(player.getWins()+1);
                    gameOver = true;
                    break;
                }
            }
        }
        resetPoints();
    }

    // Creates new game if no games were loaded
    public void newGame(int numOfPlayers) {
        System.out.println("Number of players chosen: " + numOfPlayers);
        for (int i = 1; i <= numOfPlayers; i++) {
            players.add(new Player("Player"+i));
        }
    }

    // Resets player game points when game is over
    public void resetPoints () {
        for(Player player : players) {
            player.setPoints(0);
        }
    }

    // Loads game from storage
    public void loadGame() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream inputFile = new FileInputStream(gameSave);
        ObjectInputStream input = new ObjectInputStream(inputFile);
        System.out.println(":: Game save found ::");
        try {
            while (true) {
                Player player = (Player)input.readObject();
                System.out.println("  "+player.getName()+" wins: "+player.getWins());
                players.add(player);
            }
        } catch (EOFException ex) {
        }
        gameLoaded = true;
    }

    // Saves game to storage
    public void saveGame() {
        try {
            FileOutputStream saveGame = new FileOutputStream(gameSave);
            ObjectOutputStream output = new ObjectOutputStream(saveGame);
            for (Player player : players) {
                output.writeObject(player);
            }
            output.close();
            saveGame.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Rolls die
    public int rollDie() {
        return die.roll();
    }
}