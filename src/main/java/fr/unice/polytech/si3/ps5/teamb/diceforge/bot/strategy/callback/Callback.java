package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;

/**
 * Callback
 */
public interface Callback<V, T> {

    /**
     * create a custom callback
     * 
     * @param value
     * @return
     */
    V runCallback(Context context, T value);
}