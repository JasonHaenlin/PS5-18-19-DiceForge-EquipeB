package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Create a game with 2 bot
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */

public class Game {

    private static Logger log = LogManager.getLogger(Game.class);

    private List<Bot> bots;

    private final GameMaster gameMaster;
    private String msg;
    private int round;

    /**
     * Create a game
     */
    public Game() {
        log.trace("init Game");
        bots = new ArrayList<>();
        gameMaster = new GameMaster();
        round = 1;
        msg = "";
    }

    public Game setUp(int round) {
        log.trace("round :" + round);
        this.round = round;
        return this;
    }

    public String fire() {
        log.trace("fire !");
        for (int i = 0; i < round; i++) {
            for (Bot bot : bots) {
                bot.play();

                msg += "Le bot " + bot.getName() + " lance les dés \n";
                msg += "Le bot " + bot.getName() + " a obtenue " + bot.getLastValue() + " " + bot.getLastResource()+"\n";
            }
        }
        gameMaster.etablishWinner(bots);
        msg += gameMaster.getWinnerMsg();
        return this.msg;
    }

    public Game addBot(String name) {
        log.trace("add bot :" + name);
        bots.add(new Bot(name));
        return this;
    }
}
