package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.Bot;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;

/**
 * Forge
 */
public abstract class Forge {

    protected final Bot bot;

    protected Forge(Bot bot) {
        this.bot = bot;
    }

    abstract void forge(Board board);

}