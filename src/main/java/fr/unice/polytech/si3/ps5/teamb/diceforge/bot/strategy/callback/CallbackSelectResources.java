package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Tuple;

/**
 * CallbackSelectResources
 */
public class CallbackSelectResources implements Callback<Resources, Map<Resources, Integer>> {

    @Override
    public Resources runCallback(Context context, Map<Resources, Integer> value) {
        int offsetMax = -100;
        int newOffset;
        Resources res = null;
        List<Tuple<Resources>> inv = context.getBoardView().peekInventory(context.getPlayerName());
        for (Tuple<Resources> t : inv) {
            if (value.containsKey(t.type)) {
                newOffset = t.delta(value.get(t.type));
                if (newOffset > offsetMax) {
                    offsetMax = newOffset;
                    res = t.type;
                }
            }
        }
        return res;
    }

}