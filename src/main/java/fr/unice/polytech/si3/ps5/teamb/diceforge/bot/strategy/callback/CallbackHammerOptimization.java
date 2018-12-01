package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;

/**
 * CallbackHammerOptimization
 */
public class CallbackHammerOptimization implements Callback<Integer, Integer> {

    @Override
    public Integer runCallback(Context context, Integer value) {
        return value;
    }

}