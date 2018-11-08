package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * Forge
 */
public abstract class Forge {

    protected final String id;

    protected Forge(String id) {
        this.id = id;
    }

    public abstract DiceSide compute(List<DiceSide> feasible);

}