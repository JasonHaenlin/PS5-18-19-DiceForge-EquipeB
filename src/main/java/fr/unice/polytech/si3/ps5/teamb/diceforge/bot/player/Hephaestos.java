package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.SingleRessource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

import java.util.List;

/**
 * Hephaestos, bot qui a une méthode de forge un peu plus optimisée, choisi le dé à forger
 * forge pour acheter des exploits à points de gloire
 */
public class Hephaestos extends Player{
	private Forge forge;
	private Exploit exploit;

	public Hephaestos() {
		super("Hephaestos");
	}

	@Override
	protected void setup() {
		forge = new SingleRessource(name, Resources.SUN_STONE);
		exploit = new Highest(name);
	}

	@Override
	public void play(Board boardView) {
		DiceSide sideToAdd = forge.compute(boardView.playableSides(name));
		List<DiceSide> diceSides0 = boardView.getDiceSide(name, 0);
		List<DiceSide> diceSides1 = boardView.getDiceSide(name, 1);
		forge.setDiceToForge(diceSides0, diceSides1);                                   // TODO if à améliorer
		if (boardView.forge(name, forge.getDiceToForge(), forge.removableDiceSide(diceSides0,
				diceSides1, forge.getResource()), sideToAdd)) {
			logger.debug("le bot '" + name + "' a forge et a obtenu une face " + sideToAdd.toString());
		} else {
			Card card = exploit.compute(boardView.playableCards(name));
			if (boardView.exploit(card, name)) {
				logger.debug("le bot '" + name + "' a fait un exploit et a obtenu " + card.getVictoryPoint() + " "
						+ Resources.VICTORY_POINT);
			}
		}
	}
}
