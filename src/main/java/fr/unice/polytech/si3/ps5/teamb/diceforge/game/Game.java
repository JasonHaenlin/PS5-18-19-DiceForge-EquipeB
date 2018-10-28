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

    private int finalScore;
    private int round;

    /**
     * Create a game
     */
    public Game() {
        super();
        log.debug("init Game");
        bots = new HashMap<>();
        round = 1;
    }

    public Game setup(int round) {
        log.info("round :" + round);
        this.round = round;
        return this;
    }

    public String fire() {
        log.debug("fire !");
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
        return etablishWinner();
    }

    public Game addBot(Class<? extends Player> bot) throws Exception {
        Player player = bot.newInstance();
        player.setup();
        log.info("add bot :" + bot.toString());
        bots.put(player, 0);
        registrationToBoard(player.toString(), player.hashCode());
        return this;
    }

    private String etablishWinner() {
        StringBuilder winnerMsg = new StringBuilder();
        Map<String, Integer> winners = getWinnersList();
        if (winners.size() == 1) {
            winners.forEach((name, score) -> {
                winnerMsg.append("Le bot " + name + " gagne avec " + score + " points de Gloire");
            });
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

    private Map<String, Integer> getWinnersList() {
        TreeMap<Player, Integer> sorted = new TreeMap<>(new ScoreComparator(bots));
        sorted.putAll(bots);
        Map<String, Integer> winners = new HashMap<>();
        winners.put(sorted.firstEntry().getKey().toString(), sorted.firstEntry().getValue());
        int highestScore = sorted.pollFirstEntry().getValue();
        sorted.forEach((bot, score) -> {
            if (score == highestScore) {
                winners.put(bot.toString(), score);
            }
        });
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
