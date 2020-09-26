import java.util.Scanner;
import java.util.ArrayList;

/*
 * Assignment 8
 *
 * Player class for
 * Game of Witchcraft
 *
 * @ q0r3y
 * @ 05-01-20
 *
 */

public class Player {
    // Initialize global variables
    private Room roomLocation;
    private String playerName;
    private ArrayList<Item> inventory;

    // Constructor for Player Class
    public Player() {
        setPlayerName();
        this.inventory = new ArrayList<>();
    }

    // Main method for Player Class
    public static void main(String[] args) {

    }

    // Returns items in player inventory
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // Adds items to players inventory
    public void setInventory(Item item) {
        inventory.add(item);
    }

    // Gets player current location
    public Room getRoom() {
        return roomLocation;
    }

    // Sets player location
    public void setRoomLocation(Room roomLocation) {
        this.roomLocation = roomLocation;
    }

    // Gets player name
    public String getName() {
        return playerName;
    }

    // Sets player name
    public void setPlayerName() {
        Scanner nameInput = new Scanner(System.in);
        this.playerName = nameInput.nextLine();
    }

}
