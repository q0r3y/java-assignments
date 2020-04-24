public class Card {

    private int cardNum;
    private int pin;

    public Card(int cardNum, int pin) {
        this.cardNum = cardNum;
        this.pin = pin;
    }

    public static void main(String[] args) {

    }

    public int getCardNum () {
        return cardNum;
    }

    public int getPin() {
        return pin;
    }


}

