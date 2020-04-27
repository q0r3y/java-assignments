/**
 *
 * Assignment 7 ATM
 * Code for Customer class
 *
 * @ q0r3y
 * @ 04.27.20
 */

public class Customer {

    private Card card;
    private String name;

    // Customer class constructor
    public Customer(String name, Card card) {
        this.name = name;
        this.card = card;
    }

    // Customer class main method
    public static void main(String[] args) {

    }

    // Gets customer name
    public String getName() {
        return name;
    }

    // Sets customer name
    public void setName(String newName) {
        name = newName;
    }

    // Gets customer card
    public Card getCard() {
        return card;
    }

}

