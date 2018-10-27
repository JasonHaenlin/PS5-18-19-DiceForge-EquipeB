package fr.unice.polytech.si3.ps5.teamb.diceforge.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;

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

    private static Logger LOGGER = LogManager.getLogger(Bot.class);

    private String name;

    /**
     * constructor
     */

    public Bot(String name) {
        this.name = name;
    }

    public void play(Board board) {
        // TODO
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
