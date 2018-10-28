package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;

/**
 * Forge
 */
public abstract class Forge {

    protected final String id;

    protected Forge(String id) {
        this.id = id;
    }

    abstract void forge(Board board);

}