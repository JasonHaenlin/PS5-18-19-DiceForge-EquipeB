package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.StratExploitRushHammer;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.StratForgeHighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.StratDiceSingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

public class TemplateMidGameHammerPriority implements Template {
    @Override
    public void onInitialization(Context context) {
        context.getForge().setdiceTypePriority(Resources.GOLD, Resources.MOON_STONE, Resources.SUN_STONE);
    }

    @Override
    public boolean onCondition(Context context) {
        return context.hasHammer();
    }

    @Override
    public void doAction(Context context) {
        context.getExploit().compute(new StratExploitRushHammer());
        context.getForge().compute(new StratDiceSingleResource(), new StratForgeHighestForge(), true);
    }

    @Override
    public void doElse(Context context) {
        context.getManager().nextTemplate();
    }

    @Override
    public String toString() {
        return "TemplateMidGameHammerPriority";
    }
}
