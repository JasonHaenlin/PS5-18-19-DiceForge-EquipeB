package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Forge
 */
public abstract class Forge {

    protected final String id;
    private int randomDice;

    protected Forge(String id) {
        this.id = id;
    }

    public abstract DiceSide compute(List<DiceSide> feasible);

    public DiceSide removableDiceSide(List<DiceSide> dice0, List<DiceSide> dice1, Resources resources) {
        randomDice = new Random().nextInt(2);
        if (randomDice == 0) {
            for (DiceSide diceside : dice0) {
                if (diceside.getType().ordinal() == 0 || diceside.getType().ordinal() == 1) {
                    return diceside;
                }
            }
        } else {
            for (DiceSide diceside : dice1) {
                if (diceside.getType().ordinal() == 0 || diceside.getType().ordinal() == 1) {
                    return diceside;
                }
            }
        }
        return null;
    }

}