package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * Highest
 */
public class VictoryPoint extends Forge {

    public VictoryPoint(String id) {
        super(id);
    }

    @Override
    public DiceSide compute(Board board) {
        List<DiceSide> feasible = new ArrayList<>(board.getEligibleSides(id));
        if (!feasible.isEmpty()) {
            keepOnlyVictoryPoint(feasible);
            feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.getValue(), s2.getValue()));
            return feasible.get(feasible.size() - 1);
        }
        return null;
    }

    private void keepOnlyVictoryPoint(List<DiceSide> feasible) {
        int size = feasible.size();
        for (int i = 0; i < size; i++) {
            if (!feasible.get(i).getType().equals(Resources.VICTORY_POINT)) {
                feasible.remove(i);
            }
        }
    }

}