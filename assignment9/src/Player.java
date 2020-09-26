import java.io.Serializable;

/**
 * Assignment9 Player Class
 * Creates a Player object
 *
 * @ q0r3y
 * @ 05.08.20
 */

public class Player implements Serializable
{
    int points;
    String name;
    int wins;

    /**
     * Constructor for objects of Player class
     */

    public Player(String name) {
        this.name = name;
        points = 0;
        wins = 0;
    }

    // Gets player points
    public int getPoints() {
        return points;
    }

    // Sets player points
    public void setPoints(int points) {
        this.points = points;
    }

    // Gets player name
    public String getName() {
        return name;
    }

    // Sets player name
    public void setName(String name) {
        this.name = name;
    }

    // Gets number of wins
    public int getWins() {
        return wins;
    }

    // Sets number of wins
    public void setWins(int wins) {
        this.wins = wins;
    }

}