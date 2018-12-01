package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * CallbackSelectResources
 */
public class CallbackSelectResources implements Callback<Resources, Map<Resources, Integer>> {

    @Override
    public Resources runCallback(Context context, Map<Resources, Integer> value) {
        Map<Resources, Integer> inv = context.getBoardView().peekInventory(context.getPlayerName());
        // Map<Resources, Integer> maxRes = context.getBoardView().getMaxAllowed();
        return null;
    }

}