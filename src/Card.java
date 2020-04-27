/**
 *
 * Assignment 7
 * Code for Card class
 *
 * @ q0r3y
 * @ 04.27.20
 */

public class Card {

    private int cardNum;
    private int pin;

    // Card class constructor
    public Card(int cardNum, int pin) {
        this.cardNum = cardNum;
        this.pin = pin;
    }

    // Card class main method
    public static void main(String[] args) {

    }

    // Get card number
    public int getCardNum () {
        return cardNum;
    }

    // Get pin number
    public int getPin() {
        return pin;
    }
}

