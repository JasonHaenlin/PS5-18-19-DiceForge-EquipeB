package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.ResourceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Totoro
 */
public class Totoro extends Player {
    private Forge forge;
    private Exploit exploit;

    private int randomDice = 0;

    public Totoro() {
        super("Totoro");
    }

    @Override
    protected void setup() {
        forge = new ResourceSide(name, Resources.SUN_STONE);
        exploit = new Highest(name);
    }

    @Override
    public void play(Board boardView) {
        DiceSide side = forge.compute(boardView.playableSides(name));
        if (boardView.forge(name, randomDice, forge.removableDiceSide(boardView.getDiceSide(name, 0),
                boardView.getDiceSide(name, 1), Resources.SUN_STONE), side)) {
            logger.debug("le bot '" + name + "' a forge et a obtenu une face " + side.toString());
        } else {
            Card card = exploit.compute(boardView.playableCards(name));
            if (boardView.exploit(card, name)) {
                logger.debug("le bot '" + name + "' a fait un exploit et a obtenu " + card.getVictoryPoint() + " "
                        + Resources.VICTORY_POINT);
            }
        }
    }

}