package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Tuple;

/**
 * CallbackHammerOptimization
 */
public class CallbackHammerOptimization implements Callback<Integer, Integer> {

    @Override
    public Integer runCallback(Context context, Integer value) {
        int goldNeeded = context.getBoardView().getHammerState(context.getPlayerName());
        if (goldNeeded < value)
            return value - goldNeeded;
        return isPossible(goldNeeded, value, context) ? value : 0;
    }

    private boolean isPossible(int goldNeeded, Integer value, Context context) {
        Tuple<String> round = context.getGameRound();
        return ((float) goldNeeded) / round.delta() < 2;
    }

}