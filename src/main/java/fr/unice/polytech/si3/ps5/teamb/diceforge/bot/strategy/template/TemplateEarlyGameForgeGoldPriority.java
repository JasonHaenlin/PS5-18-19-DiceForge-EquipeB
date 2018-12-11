package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.StratExploitHighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.StratForgeHighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.StratDiceSingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * templateEarlyGameForgeGoldPriority
 */
public class TemplateEarlyGameForgeGoldPriority implements Template {
    @Override
    public void onInitialization(Context context) {
        context.getForge().setdiceTypePriority(Resources.GOLD, Resources.MOON_STONE, Resources.SUN_STONE);
    }

    @Override
    public boolean onCondition(Context context) {
        return context.getGameRound().getValue() < 3;
    }

    @Override
    public void doAction(Context context) {
        context.getForge().compute(new StratDiceSingleResource(), new StratForgeHighestForge(), true);
        context.getExploit().compute(new StratExploitHighestExploit());
    }

    @Override
    public void doElse(Context context) {
        context.getManager().nextTemplate();
    }

    @Override
    public String toString() {
        return "TemplateEarlyGameForgeGoldPriority";
    }
}