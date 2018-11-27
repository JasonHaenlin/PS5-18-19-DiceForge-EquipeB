package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.SingleRessource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Hephaestos, bot qui a une méthode de forge un peu plus optimisée, choisi le
 * dé à forger forge le dé pour qque la ressource predominante soit Gold
 * pour le premier et Moon Stone pour le second
 */
public class Hephaestos extends Player {
	private Forge forge;
	private Exploit exploit;
	private Resources resourceToForge1;
	private Resources resourceToForge2;

	public Hephaestos() {
		super("Hephaestos");
	}

	@Override
	protected void setup() {
		forge = new SingleRessource(name);
		exploit = new Highest(name);
		resourceToForge1 = Resources.GOLD;
		resourceToForge2 = Resources.MOON_STONE;
	}

	@Override
	public void play() {
		DiceSide sideToAdd1 = forge.compute(boardView.playableSides(name), resourceToForge1);
		DiceSide sideToAdd2 = forge.compute(boardView.playableSides(name), resourceToForge2);

		List<DiceSide> diceSides0 = boardView.getDiceSide(name, 0);
		List<DiceSide> diceSides1 = boardView.getDiceSide(name, 1);

		int diceToForge1 = forge.choseDice(diceSides0,diceSides1,null);
		int diceToForge2 = 1; //TODO change to choseDice when changeDice is OK

		if (Game.getCurrentRound() < 4){
			forgeOrBuyExploit(diceToForge1, sideToAdd1, resourceToForge1);
		} else if (Game.getCurrentRound() < 6){
			forgeOrBuyExploit(diceToForge2, sideToAdd2, resourceToForge2);
		} else {
			buyExploitOrForge(diceToForge2, sideToAdd2, resourceToForge2);
		}
	}

	@Override
	public int callBackDice() {
		return 0;
	}

	//TODO resourceToForge isn't used at the moment check choseSideRemove
	private void forgeOrBuyExploit(int diceToForge, DiceSide sideToAdd, Resources resourceToForge) {
		if (boardView.forge(name, diceToForge, forge.choseSideRemove(boardView.getDiceSide(name, diceToForge), resourceToForge), sideToAdd)) {
			logger.debug("le bot '" + name + "' a forge et a obtenu une face " + sideToAdd.toString());
		} else {
			Card card = exploit.compute(boardView.playableCards(name));
			if (boardView.exploit(card, name)) {
				logger.debug("le bot '" + name + "' a fait un exploit et a obtenu " + card.getVictoryPoints() + " "
						+ Resources.VICTORY_POINT);
			}
		}
	}
	private void buyExploitOrForge(int diceToForge, DiceSide sideToAdd, Resources resourceToForge) {
		Card card = exploit.compute(boardView.playableCards(name));
		if (boardView.exploit(card, name)) {
			logger.debug("le bot '" + name + "' a fait un exploit et a obtenu " + card.getVictoryPoints() + " "
					+ Resources.VICTORY_POINT);
		} else {
			if (boardView.forge(name, diceToForge, forge.choseSideRemove(boardView.getDiceSide(name, diceToForge), resourceToForge), sideToAdd)) {
				logger.debug("le bot '" + name + "' a forge et a obtenu une face " + sideToAdd.toString());
			}
		}
	}
}
