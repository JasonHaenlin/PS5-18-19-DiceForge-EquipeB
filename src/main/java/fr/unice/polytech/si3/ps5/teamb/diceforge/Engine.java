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

	private static final Logger logger = LogManager.getLogger(Engine.class);
	private String confFile = "src/main/resources/configuration/allSimpleTempleFace.json";

	private Map<Player, Integer> player;
	private Map<Player, Integer> playerTotalScore;

	private int numberOfParties = 1;
	private Config conf;

	/**
	 * setup to create a new game
	 * 
	 * @param numberOfParties
	 * @return
	 * @throws Exception if the config is not found
	 */
	public Engine createGame(int numberOfParties) {
		player = new HashMap<>();
		playerTotalScore = new HashMap<>();

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
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws Exception              if the bot classe is not a player
	 */
	public Engine addBot(Class<? extends Player> bot) throws InstantiationException, IllegalAccessException {
		if (player.size() <= 3) {
			Player pl = bot.newInstance();
			player.put(pl, 0);
			playerTotalScore.put(pl, 0);
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
				playerTotalScore.replace(bot, playerTotalScore.get(bot) + map.get(bot.toString()));
				player.replace(bot, player.get(bot) + 1);
			}
		});
	}

	private String buildResult() {
		StringBuilder buildScore = new StringBuilder("Resultat de la sequence :");
		player.forEach((bot, score) -> buildScore.append("\nle bot '" + bot.toString() + "' gagne " + score + " "
				+ (score > 1 ? "parties" : "partie") + " sur " + numberOfParties + " : moyenne de "
				+ String.format("%.2f", ((float) playerTotalScore.get(bot)) / numberOfParties) + " points : "
				+ (score != 0 ? String.format("%.1f", ((float) score / numberOfParties) * 100) + "%" : "0.0%")));
		return buildScore.toString();
	}

	public void setConfFile(String confFile) {
		this.confFile = confFile;
	}

}
