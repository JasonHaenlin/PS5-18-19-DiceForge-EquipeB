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

	/**
	 *
	 */

	private static final int LOG_MAX_GAME = 1;

	private static final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	private static final Configuration config = ctx.getConfiguration();
	private static final Logger logger = LogManager.getLogger(Engine.class);
	private String confFile4P = "src/main/resources/configuration/Config.4.json";
	private String confFile3P = "src/main/resources/configuration/Config.3.json";
	private String confFile2P = "src/main/resources/configuration/Config.2.json";

	private Map<Player, Integer> player;
	private Map<Player, Integer> playerTotalScore;

	private Config conf;
	private String specificConfFile = " ";
	private int numberOfParties;

	/**
	 * setup to create a new game
	 * 
	 * @param numberOfParties
	 * @return
	 * @throws Exception if the config is not found
	 */
	public Engine createGame(int numberOfParties) throws Exception {
		this.player = new HashMap<>();
		this.playerTotalScore = new HashMap<>();
		this.numberOfParties = numberOfParties;
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

	public String chooseConFile() {
		String GreatConFile;
		switch (player.size()) {
		case 4:
			GreatConFile = confFile4P;
			break;
		case 3:
			GreatConFile = confFile3P;
			break;
		default:
			GreatConFile = confFile2P;
			break;
		}
		return GreatConFile;
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
		if (player.size() < 2) {
			logger.debug("Nombre de joueurs insufisant : fin de la partie");
			return "Error : insufficient number of players";
		}
		if (specificConfFile.equals(" ")) {
			String Confile = chooseConFile();
			this.conf = new Config(Confile);

		} else {
			this.conf = new Config(specificConfFile);
		}

		for (int i = 0; i < numberOfParties; i++) {
			logger.debug("initialisation du plateau");
			conf.prepareConfig(); // pour initialiser le plateau à chaque partie
			logger.debug("debut de la partie");
			diceForge = new Game(conf, numberofRound());
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
		// index start from 0
		if (i >= LOG_MAX_GAME - 1) {
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
		buildScore.append("\n");
		return buildScore.toString();
	}

	public void setConfFile(String confFile) {
		this.specificConfFile = confFile;
	}

}
