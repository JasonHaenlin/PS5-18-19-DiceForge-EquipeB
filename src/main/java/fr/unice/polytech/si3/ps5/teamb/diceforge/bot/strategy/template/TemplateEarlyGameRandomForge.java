package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.RandomForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.RandomSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * TemplateEarlyGameRandomForge
 */
public class TemplateEarlyGameRandomForge implements Template {

    @Override
    public void onInitialization(Context context) {
        context.getForge().setdiceTypePriority(Resources.GOLD);
    }

    @Override
    public boolean onCondition(Context context) {
        return context.getGameRound().getValue() < 3;
    }

    @Override
    public void doAction(Context context) {
        context.getForge().compute(new RandomSide(), new RandomForge(), true);
    }

    @Override
    public void doElse(Context context) {
        context.getManager().nextTemplate();
    }

}