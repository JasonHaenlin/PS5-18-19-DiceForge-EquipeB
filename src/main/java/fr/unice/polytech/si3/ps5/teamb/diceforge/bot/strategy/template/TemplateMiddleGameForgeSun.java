package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.StratExploitHighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.StratForgeHighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.StratDiceSingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * TemplateMiddleGameForgeSun
 */
public class TemplateMiddleGameForgeSun implements Template {
    @Override
    public void onInitialization(Context context) {
        context.getForge().setdiceTypePriority(Resources.SUN_STONE);
    }

    @Override
    public boolean onCondition(Context context) {
        return context.getGameRound().getValue() < 5;
    }

    @Override
    public void doAction(Context context) {
        context.getForge().compute(new StratDiceSingleResource(), new StratForgeHighestForge(), true);
        context.getExploit().compute(new StratExploitHighestExploit());
    }

    @Override
    public void doElse(Context context) {
        context.getExploit().compute(new StratExploitHighestExploit());
        context.getForge().compute(new StratDiceSingleResource(), new StratForgeHighestForge(), true);
    }

    @Override
    public String toString() {
        return "TemplateMiddleGameForgeMoonSun";
    }
}