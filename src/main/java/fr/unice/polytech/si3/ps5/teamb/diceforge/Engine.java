package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

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

	private static final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	private static final Configuration config = ctx.getConfiguration();

	private String confFile = "src/main/resources/configuration/allSimpleTempleFace.json";
	private static final Logger logger = LogManager.getLogger(Engine.class);

	private static Map<Player, Integer> player;

	private int numberOfParties = 1;
	private Config conf;

	/**
	 * create a new engine object
	 */
	public Engine() {
		//Do nothing because doesn't need to initialize attributs
	}

	/**
	 * setup to create a new game
	 * 
	 * @param numberOfParties
	 * @return
	 * @throws Exception if the config is not found
	 */
	public Engine createGame(int numberOfParties) throws Exception {
		// warn that the player map will be overide because it's a static field
		if (player != null)
			logger.warn("Player map is OVERIDE");
		player = new HashMap<>();

		this.conf = new Config(confFile);
		this.numberOfParties = numberOfParties;
		this.conf.prepareConfig();
		return this;
	}

	public int numberofRound() {
		return 9 + player.size() % 2;
	}

	/**
	 * add a new bot in the game
	 * 
	 * @param bot implemented with the abstract classe player
	 * @return
	 * @throws Exception if the bot classe is not a player
	 */
	public Engine addBot(Class<? extends Player> bot) throws Exception {
		if (player.size() <= 3) {
			player.put(bot.newInstance(), 0);
		}

		return this;
	}

	/**
	 * launch the game when the setup is ready (after createGame and addBot)
	 * 
	 * @return the result of the game (stats of the bot over the all sequence)
	 * @throws Exception
	 */
	public String fire() {
		Game diceForge;
		logger.debug("debut de la sequence");
		if (player.size() == 1 || player.size() == 0) {
			logger.debug("Nombre de joueurs insufisant : fin de la partie");
			return "Error : insufficient number of players";

		}
		for (int i = 0; i < numberOfParties; i++) {
			logger.debug("initialisation du plateau");
			this.conf.prepareConfig(); // pour initialiser le plateau à chaque partie
			logger.debug("debut de la partie");
			diceForge = new Game(this.conf, numberofRound());
			for (Entry<Player, Integer> bot : player.entrySet()) {
				diceForge.addBot(bot.getKey());
			}
			String res = diceForge.fire();
			logger.debug(res);
			computeResult(diceForge.getWinners());
			logger.debug("fin de la partie");
			removeLogger(i);
		}
		logger.debug("fin de la sequence");
		return buildResult();
	}

	private void removeLogger(int i) {
		if (i == 0) {
			config.getRootLogger().removeAppender("allInfos");
			config.getRootLogger().removeAppender("lessInfos");
			ctx.updateLoggers();
		}
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

	public void setConfFile(String confFile) {
		this.confFile = confFile;
	}

	/**
	 * get the instance of the selected player
	 * 
	 * @param name
	 * @return
	 */
	public static Player getPlayerByName(String name) {
		for (Entry<Player, Integer> pl : player.entrySet()) {
			if (pl.toString().equals(name))
				return pl.getKey();
		}
		return null;
	}
}
