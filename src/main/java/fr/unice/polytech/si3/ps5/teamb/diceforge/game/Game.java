package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.Bot;

/**
 * Create a game with 2 bot
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */

public class Game extends Board {

    private static Logger LOGGER = LogManager.getLogger(Game.class);

    private List<Bot> bots;

    private final GameMaster gameMaster;
    private int round;

    /**
     * Create a game
     */
    public Game() {
        super();
        LOGGER.debug("init Game");
        bots = new ArrayList<>();
        gameMaster = new GameMaster();
        round = 1;
    }

    public Game setUp(int round) {
        LOGGER.info("round :" + round);
        this.round = round;
        return this;
    }

    public String fire() {
        LOGGER.debug("fire !");
        for (int i = 0; i < round; i++) {
            for (Bot bot : bots) {
                bot.play(getBoardView());
                LOGGER.info("Le bot " + bot.getName() + " lance les des");
                LOGGER.info("Le bot " + bot.getName() + " a obtenue " + bot.getLastValue() + " "
                        + bot.getLastResource().toString());
            }
        }
        gameMaster.etablishWinner(bots);
        return gameMaster.getWinnerMsg();
    }

    public Game addBot(String name) {
        LOGGER.info("add bot :" + name);
        bots.add(new Bot(name));
        return this;
    }
}
