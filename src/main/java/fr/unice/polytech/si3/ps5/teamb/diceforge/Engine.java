package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class Engine {

	private static Logger logger = LogManager.getLogger(Engine.class);

	private static String confFile = "src/main/resources/configuration/basic.json";

	private Map<Player, Integer> player;
	private int numberOfParties = 1;
	private Config conf;

	public Engine() {
		this.player = new HashMap<>();
		this.conf = new Config(confFile);
	}

	public Engine createGame(int numberOfParties) {
		this.numberOfParties = numberOfParties;
		this.conf.prepareConfig();
		return this;
	}

	public Engine addBot(Class<? extends Player> bot) throws Exception {
		player.put(bot.newInstance(), 0);
		return this;
	}

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
