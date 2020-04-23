import java.util.HashMap;

public class Bank {

    private HashMap<Integer, Integer> cardBalances;

    public Bank() {
        cardBalances = new HashMap<>();
        cardBalances.put(1001,200);

    }

    public static void main(String[] args) {

    }
    // Approves transaction if enough funds available
    public boolean approveTrans(int cardNumber) {
        int maxTransAmount = cardBalances.get(cardNumber);
        return(maxTransAmount > 20);
    }

    // checks pin entered at ATM
    public boolean checkPin(Customer customer, int inputPin) {
        int cardPin = customer.getCard().getPin();
        return(inputPin == cardPin);
    }

    // Simulates insertion of card (A customer will be required to insert an ATM card)
    public boolean checkCard(int inputCard) {
        return (cardBalances.containsKey(inputCard));
    }

    public int getBalance(int cardNumber) {
        int balance = cardBalances.get(cardNumber);
        return balance;
    }

    public void removeMoney(int cardNum, int amount) {
        // Needs error handling
        int contains = cardBalances.get(cardNum);
        cardBalances.put(cardNum,(contains-amount));
    }

    public void addMoney(int cardNum, int amount) {
        // Needs error handling
        int contains = cardBalances.get(cardNum);
        cardBalances.put(cardNum,(contains+amount));
    }


}

