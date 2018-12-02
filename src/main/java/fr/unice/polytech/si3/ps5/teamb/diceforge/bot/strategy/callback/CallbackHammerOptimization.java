package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Tuple;

/**
 * CallbackHammerOptimization
 */
public class CallbackHammerOptimization implements Callback<Integer, Integer> {

    @Override
    public Integer runCallback(Context context, Integer value) {
        Tuple<Resources> goldTuple = getGoldTuple(context);
        int goldNeeded = goldTuple.delta(value);
        if (goldNeeded < 0)
            return goldNeeded;
        if (goldNeeded > 0) {

        }
        return value;
    }

    private Tuple<Resources> getGoldTuple(Context context) {
        List<Tuple<Resources>> tuple = context.getBoardView().peekInventory(context.getPlayerName());
        for (Tuple<Resources> elem : tuple) {
            if (elem.type.equals(Resources.GOLD)) {
                return elem;
            }
        }
        return null;
    }

}