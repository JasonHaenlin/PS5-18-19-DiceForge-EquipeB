package teamB.otake.diceForge;

/**
 * Create a bot
 *
 * A bot play the game
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 *
 */

public class Bot {

    private int victoryPoint;
    private Dice dice;

    /**
     * constructor
     */
    public Bot() {
        dice = new Dice();
        victoryPoint = 0;
    }

    public void play() {
        victoryPoint += rollDice();
    }

    /**
     *
     * @return the number of dice
     */

    private int rollDice() {
        return dice.random();
    }

    public int getVictoryPoint() {
        return victoryPoint;
    }
}
