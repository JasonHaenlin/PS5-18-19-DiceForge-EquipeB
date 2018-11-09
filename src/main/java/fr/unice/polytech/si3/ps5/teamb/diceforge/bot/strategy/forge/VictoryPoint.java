package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Highest
 */
public class VictoryPoint extends Forge {

    public VictoryPoint(String id) {
        super(id);
    }

    @Override
    public DiceSide compute(List<DiceSide> feasible) {
        if (!feasible.isEmpty()) {
            keepOnlyVictoryPoint(feasible);
            feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.getValue(), s2.getValue()));
            return feasible.get(feasible.size() - 1);
        }
        return null;
    }

    private void keepOnlyVictoryPoint(List<DiceSide> feasible) {
        int size = feasible.size();
        int i = 0;
        while (i < size) {
            if (!(feasible.get(i).getType().equals(Resources.VICTORY_POINT))) {
                feasible.remove(i);
                size--;
            } else {
                i++;
            }
        }
        System.out.println(feasible.toString());
    }

}