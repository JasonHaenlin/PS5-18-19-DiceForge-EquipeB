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

class Bot {

    private int victoryPoint;
    private Dice dice;

    /**
     * constructor
     */
    Bot() {
        dice = new Dice();
        victoryPoint = 0;
    }

    void play() {
        victoryPoint += rollDice();
    }

    /**
     *
     * @return the number of dice
     */

    private int rollDice() {
        return dice.random();
    }

    int getVictoryPoint() {
        return victoryPoint;
    }
}
