package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;
import java.util.Random;

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

    public abstract DiceSide compute(List<DiceSide> feasible, Resources resources);

    public abstract int analyseDice(List<DiceSide> diceSides, Resources resources);

    public abstract int choseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources);

    public abstract DiceSide choseSideRemove(List<DiceSide> dicesSides, Resources resources);

}