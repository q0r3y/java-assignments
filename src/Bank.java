import java.util.HashMap;
/**
 *
 * Assignment 7 ATM
 * Code for bank class.
 *
 * @ q0r3y
 * @ 04.27.20
 */

public class Bank {

    private HashMap<Integer, Integer> cardBalances;
    private HashMap<Integer, Boolean> lockedCards;

    // Constructor for bank class
    // Contains test card balance
    public Bank() {
        lockedCards = new HashMap<>();
        cardBalances = new HashMap<>();
        cardBalances.put(1001,200);
    }

    // Bank class main method
    public static void main(String[] args) {

    }

    // Approves transaction if enough funds available
    public boolean approveTrans(int cardNumber, int amount) {
        int maxTransAmount = cardBalances.get(cardNumber);
        return(amount <= maxTransAmount);
    }

    // checks if pin is correct
    public boolean checkPin(Customer customer, int inputPin) {
        int cardPin = customer.getCard().getPin();
        return(inputPin == cardPin);
    }
    // Checks if card number is valid
    public boolean validCardNum(int cardNumber) {
        return (cardBalances.containsKey(cardNumber));
    }

    // Checks if card is locked
    public boolean lockStatus(int cardNumber) {
        if(lockedCards.containsKey(cardNumber)) {
            return false;
        } else {
            return true;
        }
    }

    // Gets the current balance
    public int getBalance(int cardNumber) {
        int balance = cardBalances.get(cardNumber);
        return balance;
    }

    // Removes money from card
    public void removeMoney(int cardNum, int amount) {
        int contains = cardBalances.get(cardNum);
        cardBalances.put(cardNum,(contains-amount));
    }

    // Adds money to card
    public void addMoney(int cardNum, int amount) {
        // Needs error handling
        int contains = cardBalances.get(cardNum);
        if(!(amount <= 0)) {
            cardBalances.put(cardNum,(contains+amount));
        } else {
            System.out.println("You cannot add a negative amount!");
        }
    }

    // locks the card
    public void lockCard(int cardNumber) {
        lockedCards.put(cardNumber, true);
    }

}

