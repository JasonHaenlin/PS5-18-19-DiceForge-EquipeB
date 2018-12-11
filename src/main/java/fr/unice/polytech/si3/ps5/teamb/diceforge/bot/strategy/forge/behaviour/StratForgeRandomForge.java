package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour;

import java.util.List;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;

/**
 * StratForgeRandomForge
 */
public class StratForgeRandomForge implements StratForge {

    @Override
    public DiceSide execution(List<DiceSide> feasible, Resources resource) {
        if (feasible.isEmpty())
            return null;
        return feasible.get(new Random().nextInt(feasible.size()));
    }

}