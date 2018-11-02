package fr.unice.polytech.si3.ps5.teamb.diceforge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Pika;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Rem;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;

import java.util.Map;

public class Engine {
	private final int NUMBEROFGAMES = 1000;
	private boolean makeStatistics;
	private final int NUMBEROFROUNDS = 2;
	public String result = "";


	public Engine(boolean makeStatistics) {
		this.makeStatistics = makeStatistics;
	}

	public void launchGame() throws Exception {
		Game game = new Game();
		game.setup(NUMBEROFROUNDS);

		// @formatter:off
		this.result = game.setup(2)
				.addBot(Pika.class)
				.addBot(Rem.class)
				.oneGameFire();
		//@formatter:on
	}

	public void statsModeLaunchGame() throws Exception {
		int winCountPika = 0;
		int winCountRem = 0;
		int winningTotalScorePika = 0;
		int winningTotalScoreRem = 0;
		int drawCount = 0; //is multiplied by the number of players //TODO fix the player factor
		int drawTotalScore = 0;

		for (int i = 0; i < NUMBEROFGAMES; i++) {
			Game game = new Game();
			game.setup(NUMBEROFROUNDS)
					.addBot(Pika.class)
					.addBot(Rem.class);

			Map<String, Integer> interResult = game.statsModeFire();
			for (Map.Entry<String, Integer> entry : interResult.entrySet()) {
				if (interResult.size() == 1) {
					if (entry.getKey() == "Pikachu"){
						winCountPika++;
						winningTotalScorePika += entry.getValue();
					}
					else if (entry.getKey() == "Rem"){
						winCountRem++;
						winningTotalScoreRem += entry.getValue();
					}
				} else {
					drawCount++;
					drawTotalScore += entry.getValue();
				}
			}
		}

		result = "AI Pika's winrate is " + (float)winCountPika/10 + "% with a mean score of ";
		if (winCountPika != 0) result = result + (float)winningTotalScorePika/winCountPika +"\n";
		else result = "AI Pika's winrate is 0%\n";
		result = result + "AI Rem's winrate is " + (float)winCountRem/10 + "% with a mean score of ";
		if (winCountRem != 0) result = result + (float)winningTotalScoreRem/winCountRem +"\n";
		else result = "AI Rem's winrate is 0%\n";
		if (drawCount != 0) result = result + "Draw rate is " + (float)drawCount/20 + "% with a mean score of " + (float)drawTotalScore/drawCount + "\n";
		else result = result + "No draw game\n";
	}

	public String getResult() {
		return result;
	}
}
