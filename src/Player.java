
/**
 * Assignment9 Player Class
 * Creates a Player object
 *
 * @ q0r3y
 * @ 05.06.20
 */

public class Player
{
    int points;
    String name;

    /**
     * Constructor for objects of Player class
     */

    public Player(String name) {
        this.name = name;
        points = 90;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}