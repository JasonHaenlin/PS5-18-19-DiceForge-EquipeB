package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.ResourceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Pika
 */
public class Cloud extends Player {

    private Forge forge;
    private int randomDice = 0;

    public Cloud() {
        super("Cloud");
    }

    @Override
    public void setup() {
        forge = new ResourceSide(name, Resources.VICTORY_POINT);
    }

    @Override
    public void play(Board boardView) {
        DiceSide side = forge.compute(boardView.playableSides(name));
        if (boardView.forge(name, randomDice, forge.removableDiceSide(boardView.getDiceSide(name, 0),
                boardView.getDiceSide(name, 1), Resources.VICTORY_POINT), side)) {
            logger.debug("le bot '" + name + "' a forge et a obtenu une face " + side.toString());
        }
    }

}