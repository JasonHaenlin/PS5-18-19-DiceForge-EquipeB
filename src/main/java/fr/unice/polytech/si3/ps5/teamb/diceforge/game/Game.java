package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * Create a game
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */

public class Game extends Board {

    private static Logger logger = LogManager.getLogger(Game.class);

    private Map<Player, Integer> bots;
    private Map<String, Integer> winners;

    private int finalScore;
    private int round;

    /**
     * Create a game
     * 
     * @param conf
     */
    public Game(Config conf) {
        super(conf);
        logger.debug("init Game");
        this.round = 9;
        this.bots = new HashMap<>();
        this.winners = new HashMap<>();
    }

    /**
     * Launch the game
     * 
     * @return
     */
    public String fire() {
        logger.debug("oneGameFire !");
        initialize();
        for (int i = 0; i < round; i++) {
            bots.forEach((bot, score) -> {
                Map<Resources, Integer> result = rolldice(bot.toString());
                bots.replace(bot, score + getVictoryPoints(bot.toString()));
                logger.debug("Le bot '" + bot.toString() + "' lance les des");
                result.forEach((res, amout) -> logger
                        .debug("Le bot '" + bot.toString() + "' a obtenu " + amout + " " + res.toString()));
            });
            bots.forEach((bot, score) -> {
                temporaryAuthorization(bot.toString());
                startPlayerTurn(bot);
                bots.replace(bot, score + getVictoryPoints(bot.toString()));
            });
        }
        bots.forEach((bot, score) -> logger.debug("'" + bot.toString() + "'\t: " + score + " Point de Gloire"));
        return establishWinner();
    }

    private void startPlayerTurn(Player bot) {
        bot.play();
        // ask if the bot want to play again
        if (bot.replayOnceAgain() && isPlayingAgainPossible(bot.toString())) {
            logger.debug("Le bot '" + bot.toString() + "' rejoue son tour");
            removeResourcesToPlayAgain(bot.toString());
            bot.play();
        }
    }

    Game addBot(Class<? extends Player> bot) throws Exception {
        Player player = bot.newInstance();
        player.addBoard(getBoardView());
        player.setup();
        logger.info("add bot :" + bot.toString());
        bots.put(player, 0);
        registrationToBoard(player.toString(), player.hashCode());
        return this;
    }

    /**
     * add a bot to the current game and register it in the board
     * 
     * @param bot
     * @return Game
     */
    public Game addBot(Player bot) {
        bot.setup();
        bot.addBoard(getBoardView());
        logger.debug("add bot : '" + bot.toString() + "'");
        bots.put(bot, 0);
        registrationToBoard(bot.toString(), bot.hashCode());
        return this;
    }

    private String establishWinner() {
        StringBuilder winnerMsg = new StringBuilder();
        defineWinners();
        if (winners.size() == 1) {
            winners.forEach((name, score) -> winnerMsg
                    .append("Le bot '" + name + "' gagne avec " + score + " points de Gloire"));
        } else {
            winnerMsg.append("Egalite entre les joueurs ");
            winners.forEach((name, score) -> {
                winnerMsg.append(" '" + name + "'");
                finalScore = score;
            });
            winnerMsg.append(". Avec un total de " + finalScore + " points de Gloire");
        }
        return winnerMsg.toString();
    }

    private void defineWinners() {
        TreeMap<Player, Integer> sorted = new TreeMap<>(new ScoreComparator(bots));
        sorted.putAll(bots);

        winners.put(sorted.firstEntry().getKey().toString(), sorted.firstEntry().getValue());
        int highestScore = sorted.pollFirstEntry().getValue();
        sorted.forEach((bot, score) -> {
            if (score == highestScore) {
                winners.put(bot.toString(), score);
            }
        });
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
