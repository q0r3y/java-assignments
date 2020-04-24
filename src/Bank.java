import java.util.HashMap;

public class Bank {

    private HashMap<Integer, Integer> cardBalances;
    private HashMap<Integer, Boolean> lockedCards;

    public Bank() {
        lockedCards = new HashMap<>();
        cardBalances = new HashMap<>();
        cardBalances.put(1001,200);
    }

    public static void main(String[] args) {

    }

    // Approves transaction if enough funds available
    public boolean approveTrans(int cardNumber, int amount) {
        int maxTransAmount = cardBalances.get(cardNumber);
        return(amount <= maxTransAmount);
    }

    // checks pin entered at ATM
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

    public int getBalance(int cardNumber) {
        int balance = cardBalances.get(cardNumber);
        return balance;
    }

    public void removeMoney(int cardNum, int amount) {
        int contains = cardBalances.get(cardNum);
        cardBalances.put(cardNum,(contains-amount));
    }

    public void addMoney(int cardNum, int amount) {
        // Needs error handling
        int contains = cardBalances.get(cardNum);
        if(!(amount <= 0)){
            cardBalances.put(cardNum,(contains+amount));
        } else {
            System.out.println("You cannot add a negative amount!");
        }

    }

    public void lockCard(int cardNumber) {
        lockedCards.put(cardNumber, true);
    }



}

