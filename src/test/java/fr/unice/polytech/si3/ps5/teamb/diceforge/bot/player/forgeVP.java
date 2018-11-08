package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.VictoryPoint;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * forgeVP
 */
public class forgeVP extends Player {

    Forge forge;

    public forgeVP() {
        super("forgeVP");
    }

    @Override
    protected void setup() {
        forge = new VictoryPoint(name);
    }

    @Override
    public void play(Board boardView) {
        DiceSide side = forge.compute(boardView.getEligibleSides(name));
        logger.trace(side);
        boardView.forge(name, 0, boardView.getDice(name, 0).getDiceSides().get(0), side);
    }

}