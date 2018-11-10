package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * The engine if used to create a set of game. to launch a new sequence, you
 * will need to implement a bot player (1 or more)
 * 
 * <pre>
 * 
 * &#64;example
 * Engine engine = new Engine();
 * String result = engine.createGame(1000).addBot(Pika.class).addBot(Cloud.class).fire();
 * 
 * </pre>
 */
public class Engine {

	private static Logger logger = LogManager.getLogger(Engine.class);

	private static String confFile = "src/main/resources/configuration/basic.json";

	private Map<Player, Integer> player;
	private int numberOfParties = 1;
	private Config conf;

	/**
	 * create a new engine object
	 */
	public Engine() {
		this.player = new HashMap<>();
	}

	/**
	 * setup to create a new game
	 * 
	 * @param numberOfParties
	 * @return
	 * @throws Exception if the config is not found
	 */
	public Engine createGame(int numberOfParties) throws Exception {
		this.conf = new Config(confFile);
		this.numberOfParties = numberOfParties;
		this.conf.prepareConfig();
		return this;
	}

	/**
	 * add a new bot in the game
	 * 
	 * @param bot implemented with the abstract classe player
	 * @return
	 * @throws Exception if the bot classe is not a player
	 */
	public Engine addBot(Class<? extends Player> bot) throws Exception {
		player.put(bot.newInstance(), 0);
		return this;
	}

	/**
	 * launch the game when the setup is ready (after createGame and addBot)
	 * 
	 * @return the result of the game (stats of the bot over the all sequence)
	 * @throws Exception
	 */
	public String fire() throws Exception {
		Game diceForge;
		logger.debug("debut de la sequence");
		for (int i = 0; i < numberOfParties; i++) {
			logger.debug("debut de la partie");
			diceForge = new Game(this.conf);
			for (Entry<Player, Integer> bot : player.entrySet()) {
				diceForge.addBot(bot.getKey());
			}
			String res = diceForge.fire();
			logger.debug(res);
			computeResult(diceForge.getWinners());
			logger.debug("fin de la partie");
		}
		logger.debug("fin de la sequence");
		return buildResult();
	}

	private void computeResult(Map<String, Integer> map) {
		player.forEach((bot, score) -> {
			if (map.containsKey(bot.toString())) {
				player.replace(bot, player.get(bot) + 1);
			}
		});
	}

	private String buildResult() {
		StringBuilder buildScore = new StringBuilder("Resultat de la sequence :");
		player.forEach((bot, score) -> buildScore.append("\nle bot '" + bot.toString() + "' gagne " + score + " "
				+ (score > 1 ? "parties" : "partie") + " sur " + numberOfParties + " : "
				+ (score != 0 ? String.format("%.1f", ((float) score / numberOfParties) * 100) + "%" : "0.0%")));
		return buildScore.toString();
	}
}
