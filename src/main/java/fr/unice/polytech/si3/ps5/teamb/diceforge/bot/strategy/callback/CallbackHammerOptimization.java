package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TupleInteger;

/**
 * CallbackHammerOptimization
 */
public class CallbackHammerOptimization implements Callback<Integer, Integer> {

    /**
     * the value has already been limited to avoid any loss of gold by the bot. we
     * The gold needed cannot be less than the value. If it's the same value, we
     * return it. Otherwise we verify if the player will be able to finish the
     * hammer in time. If it's the case (without being sure), the total amount is
     * returned
     */
    @Override
    public Integer runCallback(Context context, Integer value) {
        int goldNeeded = context.getBoardView().getHammerState(context.getPlayerName());
        if (goldNeeded == value)
            return value;
        return isPossible(goldNeeded, value, context) ? value : 0;
    }

    private boolean isPossible(int goldNeeded, Integer value, Context context) {
        TupleInteger<String> round = context.getGameRound();
        return ((float) goldNeeded) / round.delta() < 1.5;
    }

}