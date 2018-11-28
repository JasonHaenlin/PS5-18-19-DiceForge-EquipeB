package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * StratForge
 */
public interface StratForge {
    DiceSide execution(List<DiceSide> feasible, Resources resource);

    default void keepOnlyResource(List<DiceSide> feasible, Resources resource) {
        int size = feasible.size();
        int i = 0;
        while (i < size) {
            if (!(feasible.get(i).getType().equals(resource))) {
                feasible.remove(i);
                size--;
            } else {
                i++;
            }
        }
    }
}