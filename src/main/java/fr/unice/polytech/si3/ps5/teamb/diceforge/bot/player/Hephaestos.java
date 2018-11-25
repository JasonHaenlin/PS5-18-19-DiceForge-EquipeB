package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.SingleRessource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Hephaestos, bot qui a une méthode de forge un peu plus optimisée, choisi le
 * dé à forger forge le dé pour qque la ressource predominante soit Sun Stone
 * pour le premier et Moon Stone pour le second
 */
public class Hephaestos extends Player {
	private Forge forge;
	private Exploit exploit;
	private Resources resource;

	public Hephaestos() {
		super("Hephaestos");
	}

	@Override
	protected void setup() {
		forge = new SingleRessource(name);
		exploit = new Highest(name);
		resource = Resources.SUN_STONE;
	}

	@Override
	public void play() {
		DiceSide sideToAdd = forge.compute(boardView.playableSides(name), resource);

		List<DiceSide> diceSides0 = boardView.getDiceSide(name, 0);
		List<DiceSide> diceSides1 = boardView.getDiceSide(name, 1);

		int numberDice = forge.choseDice(diceSides0,diceSides1,null);

		if (boardView.forge(name, numberDice, forge.choseSideRemove(boardView.getDiceSide(name, numberDice), resource), sideToAdd)) {
			logger.debug("le bot '" + name + "' a forge et a obtenu une face " + sideToAdd.toString());
		} else {
			Card card = exploit.compute(boardView.playableCards(name));
			if (boardView.exploit(card, name)) {
				logger.debug("le bot '" + name + "' a fait un exploit et a obtenu " + card.getVictoryPoints() + " "
						+ Resources.VICTORY_POINT);
			}
		}
	}

	@Override
	public int callBackDice() {
		return 0;
	}
}
