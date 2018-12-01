package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.SingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.StratDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * CallbackDiceWithMostResources
 */
public class CallbackDiceWithMostResources implements Callback<Integer, Resources> {

    @Override
    public Integer runCallback(Context context, Resources value) {
        StratDice d1 = new SingleResource();
        int dice = d1.choseDice(context.getBoardView().getDiceSide(context.getPlayerName(), 0),
                context.getBoardView().getDiceSide(context.getPlayerName(), 0), value);
        return dice;
    }

}