package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.HighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.HighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.SingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Hephaestos, bot qui a une méthode de forge un peu plus optimisée, choisi le
 * dé à forger forge le dé pour qque la ressource predominante soit Gold pour le
 * premier et Moon Stone pour le second
 */
public class Hephaestos extends Player {
	private Forge forge;
	private Exploit exploit;
	private int round = 0;

	public Hephaestos() {
		super("Hephaestos");
	}

	@Override
	protected void setup() {
		forge = new Forge(name, boardView);
		exploit = new Exploit(name, boardView);
		forge.setdiceTypePriority(Resources.GOLD, Resources.MOON_STONE, Resources.SUN_STONE);
	}

	/**
	 * Bot distinguish 3 phases (count round number) First phase, the bot forges
	 * gold, Second phase, the bot forges ressources And last phase, the bots does
	 * exploit
	 */
	@Override
	public void play() {
		round++;
		if (round == 3) {
			forge.setdiceTypePriority(Resources.MOON_STONE, Resources.SUN_STONE);
		}
		if (round < 5) {
			forge.compute(new SingleResource(), new HighestForge(), true);
			exploit.compute(new HighestExploit());
		} else {
			exploit.compute(new HighestExploit());
			forge.compute(new SingleResource(), new HighestForge(), true);
		}
	}

	@Override
	public int callBackDice() {
		return 0;
	}

	@Override
	protected boolean replayOnceAgain() {
		if (!boardView.playableCards(name, Resources.SUN_STONE, 2).isEmpty()) {
			round--;
			return true;
		}
		return false;
	}

	@Override
	public Resources callBackResources(List<Resources> res) {
		return res.get(0);
	}
}
