import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Random;
import java.util.ArrayList;

/*
 * Assignment 8
 *
 * Item class for
 * Game of Witchcraft
 * Creates random item from CSV file
 *
 * @ q0r3y
 * @ 04-28-20
 *
 */

public class Item {
    // Initialize global variables
    private String itemDescription;
    private ArrayList<String> itemList;

    // Constructor for Item Class
    public Item() {
        itemList = new ArrayList<>();
        buildItem();
        getRandomItem();
    }

    // Main method for Item Class
    public static void main(String[] args) {

    }

    // Gets a random Item from list of items
    public void getRandomItem() {
        Random random = new Random();
        int randInt = random.nextInt(itemList.size());
        this.itemDescription = itemList.get(randInt);
    }

    // Creates a list of items from CSV file
    public void buildItem() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("items.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] rowList = row.split(",");
                itemList.add(rowList[0]);
            }
        }
        catch(Exception e) {
            System.out.println("Exception "+e);
        }
        itemList.remove(0);
    }

    // Return item description
    public String getItemDescription() {
        return itemDescription;
    }

    // Set item description
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

}
