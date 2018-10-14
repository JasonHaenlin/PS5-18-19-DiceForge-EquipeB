package teamB.otake.diceForge;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a game with 2 bot
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */

public class Game {

    private List<Bot> bots;

    private final GameMaster gameMaster;
    private String log;
    private int round;

    /**
     * Create a game
     */
    public Game() {
        bots = new ArrayList<>();
        gameMaster = new GameMaster();
        round = 1;
        log = "";
    }

    public Game setUp(int round) {
        this.round = round;
        return this;
    }

    public String fire() {
        for (int i = 0; i < round; i++) {
            for (Bot bot : bots) {
                bot.play();
                log += "Le bot " + bot.getName() + " lance les dés \n";
                log += "Le bot " + bot.getName() + " a obtenue " + bot.getVictoryPoint() + " points de Gloire \n";
            }
        }
        gameMaster.etablishWinner(bots);
        log += gameMaster.getWinnerMsg();
        return this.log;
    }

    public Game addBot(String name) {
        bots.add(new Bot(name));
        return this;
    }
}
