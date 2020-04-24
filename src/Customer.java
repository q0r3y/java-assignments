public class Customer {

    private Card card;
    private String name;

    public Customer(String name, Card card) {
        this.name = name;
        this.card = card;
    }

    public static void main(String[] args) {

    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }

    public Card getCard() {
        return card;
    }

}

