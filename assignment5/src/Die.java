import java.util.Random;
/**
 * Assignment5 Die Class
 * Creates a Die object
 *
 * @ q0r3y
 * @ 04.21.20
 */

public class Die
{

    private int dieType;
    private Random rand = new Random();

    /**
     * Constructor for objects of class dice
     */
    public Die(int dieType) {
        this.dieType = dieType;
    }

    public int getDieType() {
        return dieType;
    }

    public void setDieType(int dieType) {
        this.dieType = dieType;
    }

    public int roll() {
        return (rand.nextInt(dieType) +1);
    }

}