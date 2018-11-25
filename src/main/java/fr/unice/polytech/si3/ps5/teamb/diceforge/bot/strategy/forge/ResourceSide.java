package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Highest
 */
public class ResourceSide extends Forge {

    public ResourceSide(String id) {
        super(id);
    }

    @Override
    public DiceSide compute(List<DiceSide> feasible, Resources resources) {
        keepOnlyResource(feasible, resources);
        if (feasible.isEmpty()) {
            return null;
        }
        feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.getValue(), s2.getValue()));
        return feasible.get(feasible.size() - 1);
    }

    @Override
    public int analyseDice(List<DiceSide> diceSides, Resources resources){
        return 0;
    }

    @Override
    public int choseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources){
        return new Random().nextInt(2);
    }

    @Override
    public DiceSide choseSideRemove(List<DiceSide> dicesSides, Resources resources){
        for (DiceSide diceside : dicesSides) {
            if (diceside.getType().ordinal() == 0 || diceside.getType().ordinal() == 1) {
                return diceside;
                }
            }
        return null;
    }

    private void keepOnlyResource(List<DiceSide> feasible, Resources resource) {
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