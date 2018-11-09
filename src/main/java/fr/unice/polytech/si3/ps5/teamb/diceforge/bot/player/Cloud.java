package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.ArrayList;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.VictoryPoint;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Pika
 */
public class Cloud extends Player {

    Forge forge;

    public Cloud() {
        super("Cloud");
    }

    @Override
    public void setup() {
        forge = new VictoryPoint(name);
    }

    @Override
    public void play(Board boardView) {
        DiceSide side = forge.compute(new ArrayList<>(boardView.getEligibleSides(name)));
        logger.trace(side);
        if (boardView.forge(name, 0, boardView.getDice(name, 0).getDiceSides().get(0), side)) {
            logger.debug("le bot '" + name + "' a forge et a obtenue " + side.toString());
        }
    }

}