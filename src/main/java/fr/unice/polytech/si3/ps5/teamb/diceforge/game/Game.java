package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    private Map<Bot, Integer> bots;

    private int finalScore;
    private int round;

    /**
     * Create a game
     */
    public Game() {
        super();
        LOGGER.debug("init Game");
        bots = new HashMap<>();
        round = 1;
    }

    public Game setup(int round) {
        LOGGER.info("round :" + round);
        this.round = round;
        return this;
    }

    public String fire() {
        LOGGER.debug("fire !");
        initialize();
        for (int i = 0; i < round; i++) {
            bots.forEach((bot, score) -> {
                bot.play(getBoardView());
                bots.replace(bot, score + getVictoryPoint(bot.getName()));
                LOGGER.info("Le bot " + bot.getName() + " lance les des");
                LOGGER.info("Le bot " + bot.getName() + " a obtenue " + bot.getLastValue() + " "
                        + bot.getLastResource().toString());
            });
        }
        return etablishWinner();
    }

    public Game addBot(String name) {
        LOGGER.info("add bot :" + name);
        Bot bot = new Bot(name);
        bots.put(bot, 0);
        registrationToBoard(bot);
        return this;
    }

    private String etablishWinner() {
        StringBuilder winnerMsg = new StringBuilder();
        Map<String, Integer> winners = getWinnersList(new TreeMap<>(new ValueComparator(bots)));
        if (winners.size() == 1) {
            winners.forEach((name, score) -> {
                winnerMsg.append("Le bot " + name + " gagne avec " + score + " points de Gloire");
            });
        } else {
            winnerMsg.append("Egalite entre les joueurs ");
            winners.forEach((name, score) -> {
                winnerMsg.append(name + " , ");
                finalScore = score;
            });
            winnerMsg.append(". Avec un total de " + finalScore);
        }
        return winnerMsg.toString();
    }

    private Map<String, Integer> getWinnersList(TreeMap<Bot, Integer> scoreMap) {
        Map<String, Integer> winners = new HashMap<>();
        winners.put(scoreMap.firstEntry().getKey().getName(), scoreMap.firstEntry().getValue());
        int highestScore = scoreMap.pollFirstEntry().getValue();
        scoreMap.forEach((bot, score) -> {
            if (score == highestScore) {
                winners.put(bot.getName(), score);
            }
        });
        return winners;
    }

}

class ValueComparator implements Comparator<Bot> {
    Map<Bot, Integer> base;

    public ValueComparator(Map<Bot, Integer> base) {
        this.base = base;
    }

    public int compare(Bot a, Bot b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
