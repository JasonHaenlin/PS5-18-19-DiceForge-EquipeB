package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template;

import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.RandomExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.RandomForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.RandomSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Context;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * TemplateFullRandom
 */
public class TemplateGameFullRandom implements Template {

    Random rnd = new Random();

    @Override
    public void onInitialization(Context context) {
        context.getForge().setdiceTypePriority(Resources.GOLD);
    }

    @Override
    public boolean onCondition(Context context) {
        return rnd.nextBoolean();
    }

    @Override
    public void doAction(Context context) {
        context.getForge().compute(new RandomSide(), new RandomForge(), true);
    }

    @Override
    public void doElse(Context context) {
        context.getExploit().compute(new RandomExploit());
    }

    @Override
    public String toString() {
        return "TemplateGameFullRandom";
    }

}