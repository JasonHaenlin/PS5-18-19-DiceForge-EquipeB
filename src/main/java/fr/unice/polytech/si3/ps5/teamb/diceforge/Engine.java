package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;

public class Engine {

	private static Logger log = LogManager.getLogger(Engine.class);

	private Game diceForge;
	private Map<Player, Integer> player;
	private int numberOfParties;

	public Engine() {
		this.player = new HashMap<>();
	}

	public Engine createGame(int numberOfParties) {
		this.numberOfParties = numberOfParties;
		return this;
	}

	public Engine addBot(Class<? extends Player> bot) throws Exception {
		player.put(bot.newInstance(), 0);
		return this;
	}

	public String fire() {
		log.info("debut de la sequence");
		for (int i = 0; i < numberOfParties; i++) {
			log.info("debut de la partie");
			this.diceForge = new Game();
			player.forEach((bot, score) -> {
				try {
					this.diceForge.addBot(bot);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			String res = this.diceForge.fire();
			log.info(res);
			computeResult(diceForge.getWinners());
			log.info("fin de la partie");
		}
		log.info("fin de la sequence");
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
		player.forEach((bot, score) -> {
			buildScore.append("\nle bot " + bot.toString() + " gagne " + score + " "
					+ (score > 1 ? "parties" : "partie") + " sur " + numberOfParties + "\t: "
					+ (score != 0 ? String.format("%.1f", ((float) score / numberOfParties) * 100) + "%" : "0.0%"));
		});
		return buildScore.toString();
	}
}
