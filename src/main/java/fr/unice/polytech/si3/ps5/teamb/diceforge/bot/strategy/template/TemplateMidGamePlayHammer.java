package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

public class TemplateMidGamePlayHammer implements Template {
    @Override
    public void onInitialization(Context context) {
        context.getForge().setdiceTypePriority(Resources.MOON_STONE, Resources.SUN_STONE);
    }

    @Override
    public boolean onCondition(Context context) {
        //TODO Methode in contest to knows if the hammer has completed
        return false;
    }

    @Override
    public void doAction(Context context) {

    }

    @Override
    public void doElse(Context context) {

    }
}
