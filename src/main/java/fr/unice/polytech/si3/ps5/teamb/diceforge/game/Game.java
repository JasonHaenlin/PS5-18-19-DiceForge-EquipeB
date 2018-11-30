package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private int round; // number of rounds in a game

    private Integer gameRound = new Integer(1);

    /**
     * Create a game
     * 
     * @param conf
     */
    public Game(Config conf, int round) {
        super(conf);
        logger.debug("init Game");
        this.round = round;
        this.bots = new LinkedHashMap<>();
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
            gameRound += 1;
            logger.debug("---------------------------------tour actuel : " + gameRound
                    + " ---------------------------------");
            bots.forEach((bot, score) -> {
                rollAllDices();
                logger.debug("------------------Debut du tour pour '" + bot.toString() + "' ------------------");
                startPlayerTurn(bot);
                bots.replace(bot, score + getVictoryPoints(bot.toString()));
                logger.debug("------------------Fin du tour pour '" + bot.toString() + "' --------------------");
            });
        }
        bots.forEach((bot, score) -> logger.debug("'" + bot.toString() + "'\t: " + score + " Point de Gloire"));
        return establishWinner();
    }

    private void rollAllDices() {
        bots.forEach((botdice, scoredice) -> {
            Map<Resources, Integer> result = rolldice(botdice);
            bots.replace(botdice, scoredice + getVictoryPoints(botdice.toString()));
            logger.debug("Le bot '" + botdice.toString() + "' lance les des");
            result.forEach((res, amout) -> logger
                    .debug("Le bot'" + botdice.toString() + "' a obtenu " + amout + " " + res.toString()));
        });
    }

    private void startPlayerTurn(Player bot) {
        temporaryAuthorization(bot.toString());
        bot.play();
        playLastCard(bot);
        // ask if the bot want to play again
        if (isPlayingAgainPossible(bot.toString()) && bot.replayOnceAgain()) {
            logger.debug("Le bot'" + bot.toString() + "'  rejoue son tour");
            removeResourcesToPlayAgain(bot.toString());
            temporaryAuthorization(bot.toString());
            bot.play();
            playLastCard(bot);
        }
    }

    Game addBot(Class<? extends Player> bot) throws InstantiationException, IllegalAccessException {
        Player player = bot.newInstance();
        return addBot(player);
    }

    /**
     * add a bot to the current game and register it in the board
     * 
     * @param bot
     * @return Game
     */
    public Game addBot(Player bot) {
        bot.addBoard(gameRound, getBoardView());
        bot.setup();
        logger.debug("add bot : '" + bot.toString() + "'");
        bots.put(bot, 0);
        registrationToBoard(bot.toString());
        return this;
    }

    private String establishWinner() {
        StringBuilder winnerMsg = new StringBuilder();
        defineWinners();
        if (winners.size() == 1) {
            winners.forEach((name, score) -> winnerMsg
                    .append("Le bot'" + name + "' gagne avec " + score + " points de Gloire"));
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
