import java.util.HashMap;
import java.util.Scanner;

public class Atm {

    //date
    //time
    //               type of transaction

//            amount
//    availble balance

    private Customer customer;
    private Bank bank;
    private Card card;
    private String machineLocation;


    public Atm() {
        String machineLocation = "Andy's Corner Store";
        customer = new Customer();
        bank = new Bank();
        card = customer.getCard();
        authenticate();
    }

    public static void main(String[] args) {
        new Atm();
    }

    // Checks input pin against correct card pin
    public void authenticate() {
        boolean correctPin = false;
        int attempt = 1;
        System.out.print("Please inert your card or enter your card number: ");
        Scanner cardInput = new Scanner(System.in);
        int cardNumber = intChecker(cardInput);
        if(bank.checkCard(cardNumber)){
            while(attempt <= 3 && !(correctPin)) {
                System.out.print("Please Enter Your Pin: ");
                Scanner inputPin = new Scanner(System.in);
                int pin = intChecker(inputPin);
                // Check bank for auth
                if (bank.checkPin(customer, pin)) {
                    correctPin = true;
                } else {
                    System.out.println("PIN Unlock Attempt: "+attempt+"/3");
                }
                attempt++;
            }
            if(correctPin) {
                transaction(customer);
            } else {
                System.out.println("\nAccount Locked. Please Contact Bank for Details.");
            }
        } else {
            System.out.println("Invalid card. Please try again later.");
        }

    }

    public void transaction(Customer customer) {
        printScreen();
        System.out.print(": ");
        Scanner inputSelection = new Scanner(System.in);
        int inputSelected = intChecker(inputSelection);
        switch (inputSelected) {
            case 1: withdraw();
                break;
            case 2: deposit();
                break;
            case 3: System.out.println("3 asdf");
                break;
            case 4: System.out.println("Please remove card.");
                break;
        }
    }

    public void withdraw() {
        System.out.println("--- WITHDRAW MENU ---");
        System.out.println("Balance: "+bank.getBalance(card.getCardNum()));
        System.out.print("Enter amount to withdraw: ");
        Scanner inputAmount = new Scanner(System.in);
        int amount = intChecker(inputAmount);
        boolean bankApproved = bank.approveTrans(card.getCardNum());
        // Checks for bank approval
        if(amount % 20 == 0 && bankApproved){
            bank.removeMoney(card.getCardNum(), amount);
            System.out.println("\nYour new balance is: "+bank.getBalance(card.getCardNum())+"\n");
        } else {
            System.out.println("\nError. Withdraw amounts must be in multiples of 20.\n");
        }
        transaction(customer);

        // get approval from bank
        // remove money from account
    }

    public void deposit() {
        System.out.println("--- DEPOSIT MENU ---");
        int cardNumber = card.getCardNum();
        System.out.println("Enter amount to deposit");
        Scanner inputAmount = new Scanner(System.in);
        int amount = intChecker(inputAmount);
        bank.addMoney(cardNumber, amount);

    }



    public void abortTrans() {
        // press cancel
    }

    public void printReceipt() {
        // after each transaction
        // print "Please take your card"
    }

    public void printScreen() {
        System.out.println("--- ATM: What would you like to do? ---");
        System.out.println("1. Make a Withdraw");
        System.out.println("2. Make a Deposit");
        System.out.println("3. Change Pin");
        System.out.println("4. Cancel");

    }

    // fix checker for strings
    public int intChecker(Scanner input) {
        int number;
        do {
            //System.out.println("Range (0-2)");
            while (!input.hasNextInt()) {
                System.out.println("INCORRECT PIN");
                input.next(); // get next input
            }
            number = input.nextInt();
        } while (number < 0);
        return number;
    }


}

