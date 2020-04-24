import java.util.Scanner;
import java.util.Date;

public class Atm {

    private Customer customer;
    private Bank bank;
    private Card card;
    private String machineLocation;
    private boolean authenticated;


    public Atm(Customer customer, Bank bank) {
        machineLocation = "Andy's Corner Store";
        this.customer = customer;
        this.bank = bank;
        card = customer.getCard();
        authenticate();
        if(authenticated) {
            menu();
        } else {
            System.out.println("\nInvalid card. Please try again later.");
        }
    }

    public static void main(String[] args) {
        new Atm((new Customer(("Howard B."),(new Card(1001, 1234)))), (new Bank()));
    }

    // Checks input pin against correct card pin
    public boolean authenticate() {
        boolean correctPin = false;
        int attempt = 1;
        System.out.print("Please inert your card or enter your card number: ");
        Scanner cardInput = new Scanner(System.in);
        int cardNumber = intChecker(cardInput);
        boolean validCard = (bank.validCardNum(cardNumber));
        boolean unlocked = (bank.lockStatus(cardNumber));
        if(validCard){
            if(unlocked) {
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
                    authenticated = true;
                } else {
                    lockCard(cardNumber);
                    System.out.println("\nToo many failed attempts! Your card has been locked.");
                    System.out.println("Please contact the bank for details.");
                }
            } else {
                System.out.println("\nAccount Locked. Please Contact Bank for Details.");
            }
        } else {
            authenticated = false;
        }
        return authenticated;
    }

    public void lockCard(int cardNumber) {
        bank.lockCard(cardNumber);
    }

    public void withdraw() {
        String transType = "Withdraw";
        System.out.println("--- WITHDRAW MENU ---");
        System.out.println("Balance: "+bank.getBalance(card.getCardNum()));
        System.out.print("Enter amount to withdraw: ");
        Scanner inputAmount = new Scanner(System.in);
        int amount = intChecker(inputAmount);
        boolean bankApproved = bank.approveTrans(card.getCardNum(), amount);
        // Checks for bank approval
        if(bankApproved) {
            if (amount % 20 == 0) {
                bank.removeMoney(card.getCardNum(), amount);
                System.out.println("\nYour new balance is: " + bank.getBalance(card.getCardNum()) + "\n");
                printReceipt(transType, amount);
            } else {
                System.out.println("\nError. Withdraw amounts must be in multiples of 20.\n");
            }
        } else {
            System.out.println("\nYou do not have enough funds available for the requested withdraw.\n");
        }

        // get approval from bank
        // remove money from account
    }

    public void deposit() {
        String transType = "Deposit";
        System.out.println("--- DEPOSIT MENU ---");
        int cardNumber = card.getCardNum();
        System.out.println("Enter amount to deposit");
        Scanner inputAmount = new Scanner(System.in);
        int amount = intChecker(inputAmount);
        bank.addMoney(cardNumber, amount);
        printReceipt(transType, amount);
    }

    public void menu() {
        boolean cancel = false;
        while(!cancel) {
            System.out.println("--- ATM: What would you like to do? ---");
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("1. Make a Withdraw");
            System.out.println("2. Make a Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Cancel");
            System.out.print(": ");
            Scanner inputSelection = new Scanner(System.in);
            int inputSelected = intChecker(inputSelection);
            boolean validSelection = inputSelected <= 4 && inputSelected > 0;
            if (validSelection) {
                switch (inputSelected) {
                    case 1:
                        withdraw();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        System.out.println("\nCurrent Balance: " + getBalance(card.getCardNum()) + "\n");
                        break;
                    case 4:
                        System.out.println("Please remove card.");
                        cancel = true;
                }
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    public void printReceipt(String transType, int amount) {
        Date today = new Date();
        System.out.println("Printing receipt...\n");
        System.out.println("---------------");
        System.out.println("- "+machineLocation+" ATM");
        System.out.println("- "+today);
        System.out.println("- Card Number: "+card.getCardNum());
        System.out.println("- Transaction Type: "+transType);
        System.out.println("- Amount: "+amount);
        System.out.println("- Remaining balance: "+bank.getBalance(card.getCardNum()));
        System.out.println("---------------\n");

    }

    public int getBalance(int cardNumber) {
        return(bank.getBalance(cardNumber));
    }

    // fix checker for strings
    public int intChecker(Scanner input) {
        int number;
        do {
            while (!input.hasNextInt()) {
                System.out.println("Input only accepts integers!");
                input.next();
            }
            number = input.nextInt();
        } while (number < 0);
        return number;
    }


}

