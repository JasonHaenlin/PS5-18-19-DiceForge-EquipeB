package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.ResourceSide;
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
    private Resources resource;

    private int randomDice = 0;

    public Totoro() {
        super("Totoro");
    }

    @Override
    protected void setup() {
        forge = new ResourceSide(name);
        exploit = new Highest(name);
        resource = Resources.SUN_STONE;
    }

    @Override
    public void play() {
        DiceSide side = forge.compute(boardView.playableSides(name), resource);

        int numberDice = forge.choseDice(boardView.getDiceSide(name,0),
                boardView.getDiceSide(name,1), resource);

        if (boardView.forge(name, randomDice,
                forge.choseSideRemove(boardView.getDiceSide(name, numberDice), resource),side)) {
            logger.debug("le bot '" + name + "' a forge et a obtenu une face " + side.toString());
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