
/*
 * Assignment 8
 *
 * Room class for
 * Game of Witchcraft
 *
 * Creates a room from csv file data
 * Adds items and occupancy based on randomness
 *
 * @ q0r3y
 * @ 04-28-20
 *
 */

public class Room {
    // Initialize global variables
    private String roomNumber;
    private String roomName;
    private String roomDescription;
    private String chanceOccupied;
    private String chanceItem;
    private String ifOccupied;
    private String numOfDoors;
    private String lowRoom;
    private String highRoom;
    private Item roomItem;
    private final double randomOccupied;
    private final double randomItem;

    // Constructor for Room Class
    public Room(String[] roomData) {
        this.roomNumber = roomData[0];
        this.roomName = roomData[1];
        this.roomDescription = roomData[2];
        this.chanceOccupied = roomData[3];
        this.chanceItem = roomData[4];
        this.ifOccupied = roomData[5];
        this.numOfDoors = roomData[6];
        this.lowRoom = roomData[7];
        this.highRoom = roomData[8];
        randomItem = (Math.random() * 100);
        createItem();
        randomOccupied = (Math.random() * 100);
    }

    // Main method for Room Class
    public static void main(String[] args) {
        double foo = Math.random() * 100;
        System.out.println((foo));
    }

    // Returns true if randomOccupied hits or exceeds chanceOccupied percentage
    public boolean roomOccupied() {
        return !(randomOccupied < Integer.parseInt(chanceOccupied));
    }

    // Creates item in room if randomItem hits or exceeds chanceItem percentage
    public void createItem() {
        if(randomItem < Integer.parseInt(chanceItem)) {
            roomItem = new Item();
        } else {
            roomItem = null;
        }
    }

    // Gets item in room
    public Item getItem() {
        return roomItem;
    }

    // Sets item in room
    public void setItem(Item item) {
        this.roomItem = item;
    }

    // Removes item from room
    public void removeItem() {
        this.roomItem = null;
    }

    // Returns room has item boolean
    public boolean hasItem() {
        return roomItem != null;
    }

    // Gets room name
    public String getRoomName() {
        return roomName;
    }

    // Sets room name
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    // Gets room description
    public String getRoomDescription() {
        return roomDescription;
    }

    // Sets room description
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    // Returns String if the room is occupied
    public String getIfOccupied() {
        return ifOccupied;
    }

    // Set occupied string
    public void setIfOccupied(String ifOccupied) {
        this.ifOccupied = ifOccupied;
    }

    // Gets Room Number
    public Integer getRoomNumber() {
        return Integer.parseInt(roomNumber);
    }

    // Sets Room Number
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Gets the percentage chance the room is occupied
    public Integer getChanceOccupied() {
        return Integer.parseInt(chanceOccupied);
    }

    // Sets the percentage chance the room is occupied
    public void setChanceOccupied(String chanceOccupied) {
        this.chanceOccupied = chanceOccupied;
    }

    // Gets the chance an item is in room
    public Integer getChanceItem() {
        return Integer.parseInt(chanceItem);
    }

    // Sets the chance an item is in room
    public void setChanceItem(String chanceItem) {
        this.chanceItem = chanceItem;
    }

    // Get number of doors adjacent to the room
    public Integer getNumOfDoors() {
        return Integer.parseInt(numOfDoors);
    }

    // Set the number of doors adjacent to the room
    public void setNumOfDoors(String numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    // Gets the lowest adjacent room number
    public Integer getLowRoom() {
        return Integer.parseInt(lowRoom);
    }

    // Sets the lowest adjacent room number
    public void setLowRoom(String lowRoom) {
        this.lowRoom = lowRoom;
    }

    // Gets the highest adjacent room number
    public Integer getHighRoom() {
        return Integer.parseInt(highRoom);
    }

    // Sets the highest adjacent room number
    public void setHighRoom(String highRoom) {
        this.highRoom = highRoom;
    }
}
