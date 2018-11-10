package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Temple
 */
public class Temple {
    private List<Pool> sidesAvailable;

    public Temple(Map<Integer, List<DiceSide>> sides) {
        putInTemple(sides);
    }

    /**
     * store each sides in the right pool
     * 
     * @param sides
     */
    private void putInTemple(Map<Integer, List<DiceSide>> sides) {
        this.sidesAvailable = new ArrayList<>();
        sides.forEach((cost, side) -> this.sidesAvailable.add(new Pool(side, cost)));
    }

    /**
     * retrieve the available sides based on the cost. It will retrieve all the pool
     * from the less expensive to the max cost
     * 
     * @param cost
     * @return the list of available sides
     */
    public List<DiceSide> obtainReplaceableSides(int cost) {
        List<DiceSide> available = new ArrayList<>();
        for (int i = 1; i <= cost; i++) {
            Pool p = RetrievePool(i);
            if (p != null) {
                available.addAll(p.getSides());
            }
        }
        return available;
    }

    /**
     * remove a side from a pool
     * 
     * @param sideToRemove
     * @return true if the side has been removed
     */
    public boolean removeSide(DiceSide sideToRemove) {
        int cost = sideToRemove.getCost();
        Pool curPool = RetrievePool(cost);
        if (curPool == null) {
            return false;
        }
        return curPool.tryToRemoveSide(sideToRemove);
    }

    /**
     * retrieve the right pool based on the cost
     * 
     * @param cost
     * @return the pool
     */
    private Pool RetrievePool(int cost) {
        for (Pool p : sidesAvailable) {
            if (p.getCost() == cost) {
                return p;
            }
        }
        return null;
    }
}