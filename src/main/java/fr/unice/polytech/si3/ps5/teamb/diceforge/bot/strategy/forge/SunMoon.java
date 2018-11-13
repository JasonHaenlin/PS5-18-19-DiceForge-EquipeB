package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * SunMoon
 */
public class SunMoon extends Forge {

    public SunMoon(String id) {
        super(id);
    }

    @Override
    public DiceSide compute(List<DiceSide> feasible) {
        keepOnlySunAndMoon(feasible);
        if (feasible.isEmpty()) {
            return null;
        }
        feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.getValue(), s2.getValue()));
        return feasible.get(feasible.size() - 1);
    }

    private void keepOnlySunAndMoon(List<DiceSide> feasible) {
        int size = feasible.size();
        int i = 0;
        while (i < size) {
            if (!(feasible.get(i).getType().equals(Resources.SUN_STONE))
                    || !(feasible.get(i).getType().equals(Resources.MOON_STONE))) {
                feasible.remove(i);
                size--;
            } else {
                i++;
            }
        }
    }

}