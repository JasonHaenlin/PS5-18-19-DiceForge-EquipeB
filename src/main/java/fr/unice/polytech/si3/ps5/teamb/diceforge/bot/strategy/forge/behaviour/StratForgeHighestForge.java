package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;

/**
 * StratForgeHighestForge chooses the side with the highest gold value
 * 
 * @see StratForge
 */
public class StratForgeHighestForge implements StratForge {

    @Override
    public DiceSide execution(List<DiceSide> feasible, Resources resource) {
        keepOnlyResource(feasible, resource);
        if (feasible.isEmpty()) {
            return null;
        }
        feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.coefficient(), s2.coefficient()));
        return feasible.get(feasible.size() - 1);
    }

}