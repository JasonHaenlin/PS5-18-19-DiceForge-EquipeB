package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

public class Game extends Board {

    private static Logger log = LogManager.getLogger(Game.class);

    private Map<Player, Integer> bots;
    private Map<String, Integer> winners;

    private int finalScore;
    private int round;

    /**
     * Create a game
     */
    public Game() {
        super();
        log.debug("init Game");
        this.round = 5;
        this.bots = new HashMap<>();
        this.winners = new HashMap<>();
    }

    public String fire() {
        log.debug("oneGameFire !");
        initialize();
        for (int i = 0; i < round; i++) {
            bots.forEach((bot, score) -> {
                Map<Resources, Integer> result = rolldice(bot.toString());
                bots.replace(bot, score + getVictoryPoint(bot.toString()));
                log.info("Le bot " + bot.toString() + " lance les des");
                result.forEach((res, amout) -> log
                        .info("Le bot " + bot.toString() + " a obtenue " + amout + " " + res.toString()));
            });
            bots.forEach((bot, score) -> {
                bot.play(getBoardView());
                bots.replace(bot, score + getVictoryPoint(bot.toString()));
            });
        }
        bots.forEach((bot, score) -> log.info(bot.toString() + "\t: " + score + " Point de Gloire"));
        return establishWinner();
    }

    public Game addBot(Player bot) throws Exception {
        bot.setup();
        log.info("add bot :" + bot.toString());
        bots.put(bot, 0);
        registrationToBoard(bot.toString(), bot.hashCode());
        return this;
    }

    private String establishWinner() {
        StringBuilder winnerMsg = new StringBuilder();
        Map<String, Integer> winners = defineWinners();
        if (winners.size() == 1) {
            winners.forEach(
                    (name, score) -> winnerMsg.append("Le bot " + name + " gagne avec " + score + " points de Gloire"));
        } else {
            winnerMsg.append("Egalite entre les joueurs ");
            winners.forEach((name, score) -> {
                winnerMsg.append(" [" + name + "]");
                finalScore = score;
            });
            winnerMsg.append(". Avec un total de " + finalScore + " points de Gloire");
        }
        return winnerMsg.toString();
    }

    private Map<String, Integer> defineWinners() {
        TreeMap<Player, Integer> sorted = new TreeMap<>(new ScoreComparator(bots));
        sorted.putAll(bots);

        winners.put(sorted.firstEntry().getKey().toString(), sorted.firstEntry().getValue());
        int highestScore = sorted.pollFirstEntry().getValue();
        sorted.forEach((bot, score) -> {
            if (score == highestScore) {
                winners.put(bot.toString(), score);
            }
        });
        return winners;
    }

    /**
     * @return the winners
     */
    public Map<String, Integer> getWinners() {
        return winners;
    }

}

class ScoreComparator implements Comparator<Player> {
    Map<Player, Integer> base;

    public ScoreComparator(Map<Player, Integer> base) {
        this.base = base;
    }

    public int compare(Player a, Player b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
