import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Assignment9 Pig Class
 * Creates a Pig object
 *
 * @ q0r3y
 * @ 05.06.20
 */

public class Pig
{
    private Die die;
    private ArrayList<Player> players;
    private boolean gameOver;
    private boolean gameLoaded;

    /**
     * Constructor for objects of Pig class
     */

    public Pig(int numOfPlayers) {
        loadGame();
        if(!gameLoaded) {
            this.die = new Die();
            this.players = new ArrayList<>();
            System.out.println("Number of players chosen: " + numOfPlayers);
            for (int i = 1; i <= numOfPlayers; i++) {
                System.out.print("Player " + i + " enter name: ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                players.add(new Player(name));
            }
            this.gameOver = false;
        }
        playGame();
    }

    public static void main(String[] args) {
        new Pig(2);
    }

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
                            // get input if else
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
                    gameOver = true;
                    break;
                }
            }
        }
    }

    public void loadGame() {

        gameLoaded = false;
    }

    public void saveGame() {


    }

    public int rollDie() {
        return die.roll();
    }

}