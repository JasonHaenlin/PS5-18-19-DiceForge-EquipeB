package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.VictoryPoint;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
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
        forge = new VictoryPoint(name);
    }

    @Override
    public void play(Board boardView) {
        DiceSide side = forge.compute(boardView.playableSides(name));
        if (boardView.forge(name, randomDice, removableDiceSide(boardView), side)) {
            logger.debug("le bot '" + name + "' a forge et a obtenu une face " + side.toString());
        }
    }

    public DiceSide removableDiceSide(Board boardView) {
        randomDice = new Random().nextInt(2);
        if (randomDice == 0) {
            for (DiceSide diceside : boardView.getDiceSide(name, 0)) {
                if (diceside.getType().ordinal() == 3 || diceside.getType().ordinal() == 4) {
                    return diceside;
                }
            }
        } else {
            for (DiceSide diceside : boardView.getDiceSide(name, 1)) {
                if (diceside.getType().ordinal() == 3 || diceside.getType().ordinal() == 4) {
                    return diceside;
                }
            }
        }
        return null;
    }

}