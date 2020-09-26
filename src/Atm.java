import java.util.Scanner;
import java.util.Date;
/**
 *
 * Assignment 7 ATM
 * Code for ATM class. Accepts customer, and bank objects
 *
 * @ q0r3y
 * @ 04.27.20
 */

public class Atm {

    private Customer customer;
    private Bank bank;
    private Card card;
    private String machineLocation;
    private boolean authenticated;

    // Constructor for ATM class
    public Atm(Customer customer, Bank bank) {
        machineLocation = "Andy's Corner Store";
        this.customer = customer;
        this.bank = bank;
        card = customer.getCard();
        if(authenticate()) {
            menu();
        } else {
            System.out.println("\nInvalid card. Please try again later.");
        }
    }

    // Main method for ATM class, currently contains test parameters
    public static void main(String[] args) {
        new Atm((new Customer(("Howard B."),(new Card(1001, 1234)))), (new Bank()));
    }

    // Authenticates card number and pin with bank system
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

    // Locks the card
    public void lockCard(int cardNumber) {
        bank.lockCard(cardNumber);
    }

    // withdraw method
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
    }

    // deposit method
    public void deposit() {
        String transType = "Deposit";
        System.out.println("--- DEPOSIT MENU ---");
        int cardNumber = card.getCardNum();
        System.out.print("Enter amount to deposit: ");
        Scanner inputAmount = new Scanner(System.in);
        int amount = intChecker(inputAmount);
        bank.addMoney(cardNumber, amount);
        printReceipt(transType, amount);
    }

    // menu method
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

    // Prints a receipt with important information
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

    // Gets the balance
    public int getBalance(int cardNumber) {
        return(bank.getBalance(cardNumber));
    }

    // Checks for positive integer input
    public int intChecker(Scanner input) {
        int number;
        do {
            while (!input.hasNextInt()) {
                System.out.print("Input only accepts positive integers! Please try again: ");
                input.next();
            }
            number = input.nextInt();
            if(number < 0){
                System.out.print("Input only accepts positive integers! Please try again: ");
            }
        } while (number < 0);
        return number;
    }
}

