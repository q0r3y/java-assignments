import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/*
 * Assignment 8
 *
 * Main game logic for
 * Game of Witchcraft
 *
 * @ q0r3y
 * @ 05-01-20
 *
 */

public class Game {
    // Initialize global variables
    private Player player;
    private ArrayList<Room> roomList;
    private boolean endGame;

    // Constructor for Game Class
    public Game() {
        roomList = new ArrayList<>();
        endGame = false;
        startGame();
    }

    // Main method for Game Class
    public static void main(String[] args) {
        new Game();
    }

    // Starts School of Witchcraft
    public void startGame() {
        welcomeScreen();
        getCsvData();
        playerCreation();
        playGame();
    }

    // Prints Welcome Screen
    public void welcomeScreen() {
        System.out.println("\n''```````````````````````````````````````````````````````''");
        System.out.println("''  Welcome to MagicCaster! A text based adventure game  ''");
        System.out.println("''  that takes place at Hogwarts school of Witchcraft    ''");
        System.out.println("''  and Wizardry. Your objective as an explorer is to    ''");
        System.out.println("''  navigate through the rooms to see what cool objects  ''");
        System.out.println("''  and mysteries you can uncover. When entering a room  ''");
        System.out.println("''  there's a small chance you may be randomly           ''");
        System.out.println("''  transported to another room in the castle.           ''");
        System.out.println("'',,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,''\n");
    }

    // Reads CSV data and adds room data to an Array
    public void getCsvData() {
        ArrayList<String[]> csvData = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("rooms.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] rowList = row.split(",");
                csvData.add(rowList);
            }
            csvReader.close();
        }
        catch(Exception e){
            System.out.println("Exception "+e);
        }
        csvData.remove(0);
        // Calls build room method
        buildRooms(csvData);
    }

    // Creates Room objects from input csvData
    public void buildRooms(ArrayList<String[]> csvData) {
        int i = 0;
        while(i <= csvData.size()-1) {
            String[] roomData = (csvData.get(i));
            Room newRoom = new Room(roomData);
            roomList.add(newRoom);
            i++;
        }
    }

    // Initial Player Creation
    public void playerCreation() {
        System.out.print("| Greetings Spellcaster! What is thy name?\n"+": ");
        this.player = new Player();
        System.out.println("| Great to meet you, "+player.getName()+"!\n");
        player.setRoomLocation(roomList.get(0));
    }

    // Main gameplay loop
    public void playGame() {
        while(!endGame) {
            printLocation();
            printSurroundings();
            printInventory();
            printRooms();
            int selection = selectionPrompt();
            if(selection != -1) {
                Room selectedRoom = selectRoom(selection);
                movePlayer(selectedRoom);
            } else {
                endGame();
            }
        }
    }

    // Prints players current location
    public void printLocation() {
        System.out.println("| You have entered - "+player.getRoom().getRoomName()+"\n");
    }

    // Prints room data
    public void printSurroundings() {
        System.out.println("|     ROOM     | "+player.getRoom().getRoomDescription());
        if(player.getRoom().hasItem()) {
            System.out.println("|  ROOM ITEMS  | Found Item: "+player.getRoom().getItem().getItemDescription()+". Added to inventory.");
            playerAddItem();
        } else {
            System.out.println("|  ROOM ITEMS  | No Items.");
        }
        if(player.getRoom().roomOccupied()) {
            System.out.println("|   OCCUPANCY  | "+player.getRoom().getIfOccupied());
        } else {
            System.out.println("|   OCCUPANCY  | No Occupants.");
        }
    }

    // Adds items found in rooms to players inventory
    public void playerAddItem() {
        boolean playerHasItem = false;
        ArrayList<Item> playerInventory = player.getInventory();
        int i = 0;
        if(player.getInventory().size() != 0){
            while(!playerHasItem && (i <= playerInventory.size()-1)) {
                if(playerInventory.get(i).equals(player.getRoom().getItem())) {
                    playerHasItem = true;
                }
                i++;
            }
        }
        if(!playerHasItem) {
            player.setInventory(player.getRoom().getItem());
            player.getRoom().removeItem();
        }
    }

    // Prints the players current inventory
    public void printInventory() {
        System.out.print("|   INVENTORY  | ");
        for(Item i : player.getInventory()) {
            System.out.print(i.getItemDescription()+", ");
        }
    }

    // Prints rooms within vicinity of the player.
    public void printRooms() {
        int lowRoom = player.getRoom().getLowRoom();
        int highRoom = player.getRoom().getHighRoom();
        if(lowRoom != highRoom){
            System.out.println("\n\n| From this room you may choose to enter rooms "+lowRoom+"-"+highRoom);
        } else {
            System.out.println("\n\n| From this room you may choose to enter hallway "+lowRoom);
        }
    }

    // Gets input for room selection
    public int selectionPrompt() {
        int lowRoom = player.getRoom().getLowRoom();
        int highRoom = player.getRoom().getHighRoom();
        System.out.print("| Use ("+lowRoom+"-"+highRoom+") to explore (0 to return to main hall. -1 to quit.)\n: ");
        Scanner getInput = new Scanner(System.in);
        int selection = getInput(getInput, lowRoom, highRoom);
        System.out.println("");
        return selection;
    }

    // Validates input for accepted conditions
    public int getInput(Scanner input, int lowNum, int highNum) {
        int number;
        do {
            // Checks for anything other than integer
            while (!input.hasNextInt()) {
                System.out.println("| Quit being foolish, " + player.getName());
                System.out.print(": Enter a valid door number ("+lowNum+"-"+highNum+")(0 to return to main hall. -1 to quit): ");
                input.next();
            }
            number = input.nextInt();
            // Checks for quit command
            if(number == -1) {
                return -1;
            }
            // Checks that integer is in range, or 0
            if (number < lowNum || number > highNum) {
                if (number != 0) {
                    System.out.println("| Quit being foolish, " + player.getName());
                    System.out.print(": Enter a valid door number ("+lowNum+"-"+highNum+")(0 to return to main hall. -1 to quit): ");
                }
            }
        } while (number != 0 && (number < lowNum || number > highNum));
        // 5% chance to teleport to another room.
        Random random = new Random();
        double randomTeleport = (Math.random() * 100);
        int randNum = random.nextInt(roomList.size());
        if(randomTeleport > 95) {
            System.out.print("\n| ~~~ Whoa! You've magically been transported to another room! ~~~");
            return randNum;
        } else {
            return number;
        }
    }

    // Selects room from index
    public Room selectRoom(int index) {
        return roomList.get(index);
    }

    // Moves the player to selected room
    public void movePlayer(Room selection) {
        player.setRoomLocation(selection);
    }

    // Ends the Game
    public void endGame() {
        System.out.println("\n''```````````````````````````````````````````````````````''");
        System.out.println("''    Thank you for playing MagicCaster! Safe Travels!   ''");
        System.out.println("'',,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,''\n");
        endGame = true;
    }
}
