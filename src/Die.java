import java.util.Random;
/**
 * Assignment8 Die Class
 * Creates a Die object
 *
 * @ q0r3y
 * @ 05.06.20
 */

public class Die
{
    private int dieSides;
    private Random rand = new Random();

    /**
     * Constructor for objects of class dice
     */

    public Die() {
        this.dieSides = 6;
    }

    public int getDieSides() {
        return dieSides;
    }

    public void setDieSides(int dieSides) {
        this.dieSides = dieSides;
    }

    public int roll() {
        return (rand.nextInt(dieSides) +1);
    }

}