import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * Assignment 5 DiceRoller Class
 * Accepts a die type, number of dice, and a target number
 *
 * @ q0r3y
 * @ 04.21.20
 */

public class DiceRoller {
    // Initialize Instance Variables
    private ArrayList<Die> diceArray;
    private int targetNum;
    final int[] VALIDDIETYPE;
    final int MAXDICE;
    final int MINTARGETNUM;
    final int MAXTARGETNUM;

    /**
     * Constructor for objects of class DiceRoller
     */
    public DiceRoller(int dieType, int numOfDice, int targetNum) {
        // Declare Class Variables
        diceArray = new ArrayList<Die>();
        VALIDDIETYPE = new int[]{4, 6, 8, 10, 12, 20, 100};
        MAXDICE = 10;
        MINTARGETNUM = 5;
        MAXTARGETNUM = 30;

        printRules();

        // Run check for valid input
        if (validator(dieType, numOfDice, targetNum)) {
            int i = 1;
            while (i <= numOfDice) {
                Die die = new Die(dieType);
                diceArray.add(die);
                i++;
            }
            this.targetNum = targetNum;
            System.out.println("YOUR TARGET NUMBER: "+targetNum+"\n");
        } else {
            System.out.println("The settings you've entered are invalid. Please read the rules and try again.") ;
        }
    }

    public static void main(String[] args) {
        DiceRoller diceRoller = new DiceRoller(4, 10, 7);
        diceRoller.rollDice();
    }

    // Validator checks that all input data matches game criteria
    public boolean validator(int dieType, int numOfDice, int targetNum) {

        boolean valid = true;
        // Valid Type of Die Selection Logic
        int i = 0;
        while (i <= (VALIDDIETYPE.length) - 1) {
            if (dieType == VALIDDIETYPE[i]) {
                valid = true;
                break;
            } else {
                valid = false;
            }
            i++;
        }
        // Valid Number of Dice Selection Logic
        if (numOfDice > MAXDICE || numOfDice < 1) {
            valid = false;
            System.out.println("Number of dice must be between 1 and 10!");
        }
        // Valid Target Number Selection Logic
        if (targetNum < MINTARGETNUM || targetNum > MAXTARGETNUM) {
            valid = false;
            System.out.println("Target number must be between 5 and 30!");
        }
        return valid;
    }

    public void printRules() {
        System.out.println("\n--- This is a dice rolling game ---");
        System.out.println("\n========Rules========");
        System.out.println("Valid Die Types: " + Arrays.toString(VALIDDIETYPE));
        System.out.println("Max number of dice: " + MAXDICE);
        System.out.println("Min & Max Target Numbers: " + MINTARGETNUM + " & " + MAXTARGETNUM+"\n");
        System.out.println("1. If more than 50% of the dice are ones, the result is a bust and the roll fails.");
        System.out.println("2. If any of the results are the same as the die type, that individual result is open ended");
        System.out.println("   and another dice is rolled and added to the first result. This can happen multiple times.");
        System.out.println("=====================\n");
    }

    public void rollDice() {
        ArrayList<Integer> rollArray = new ArrayList<Integer>();
        ArrayList<Integer> listOfOnes = new ArrayList<Integer>();

        // Rolls each die in diceArray
        int dieNumber = 0;
        while (dieNumber <= diceArray.size()-1) {

            Die currentDie = diceArray.get(dieNumber);
            int rollValue = currentDie.roll();
            int roll = rollValue;

            // Checks if you rolled the target number
            if(roll == targetNum) {
                System.out.println("~~~ DICE NUMBER "+(dieNumber+1)+" ROLLED THE TARGET NUMBER ~~~");
                System.out.println("~~~~ CONGRATULATIONS YOU WIN THE GAME! ~~~~");
                break;
            }
            // Checks if the roll is equal to the die type
            if(roll != currentDie.getDieType()) {
                System.out.println("Die "+(dieNumber+1)+" rolled: "+roll+"\n");
            } else {
                System.out.println(".... Die "+(dieNumber+1)+" rolled the die type! "+"("+(currentDie.getDieType())+")");
                System.out.println(".... You re-roll this die and that value will be added to the last roll.");
                // Re-rolls and adds the re-roll value to current roll if the roll is equal to the die type
                while (rollValue == currentDie.getDieType()) {
                    rollValue = currentDie.roll();
                    roll += rollValue;
                    System.out.println(".... Your Re-roll is: " + rollValue + "\n.... Current Roll for this die is: " + roll + "\n");
                }
                System.out.println("Die "+(dieNumber+1)+" rolled: "+roll+"\n");
            }
            // Checks if the roll value is a 1
            if(rollValue == 1) {
                listOfOnes.add(1);
            }
            rollArray.add(roll);
            dieNumber++;
        }
        // Prints lose statement
        if((rollArray.size() / 2) < listOfOnes.size()){
            System.out.println("--- More than half of your die rolls resulted in a 1! You lose! ---");
        } else if(dieNumber == diceArray.size() && dieNumber != 0) {
            System.out.println("--- You never rolled the target number! You lose! ---");
        }
    }
}



