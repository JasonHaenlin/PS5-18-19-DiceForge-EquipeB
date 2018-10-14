package teamb.otake.diceforge;

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
    private String name;

    /**
     * constructor
     */
    Bot() {
        dice = new Dice();
        victoryPoint = 0;
        name = "bot";
    }

    Bot(String name) {
        dice = new Dice();
        victoryPoint = 0;
        this.name = name;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Bot)) {
            return false;
        }
        Bot bot = (Bot) obj;
        return this.getVictoryPoint() == bot.getVictoryPoint();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
