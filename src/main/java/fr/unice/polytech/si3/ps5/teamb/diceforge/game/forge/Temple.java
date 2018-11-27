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
    private List<DiceSide> cardAlreadyPlayed;

    public Temple(Map<Integer, List<DiceSide>> sides) {
        putInTemple(sides);
        cardAlreadyPlayed = new ArrayList<>();
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
            Pool p = retrievePool(i);
            if (p != null) {
                addAllAuthSide(available, p.getSides());
            }
        }
        return available;
    }

    private void addAllAuthSide(List<DiceSide> available, List<DiceSide> sides) {
        sides.forEach(side -> {
            if (!cardAlreadyPlayed.contains(side))
                available.add(side);
        });
    }

    public void resetTurn() {
        cardAlreadyPlayed = new ArrayList<>();
    }

    /**
     * remove a side from a pool
     * 
     * @param sideToRemove
     * @return true if the side has been removed
     */
    public boolean removeSide(DiceSide sideToRemove) {
        int cost = sideToRemove.getCost();
        Pool curPool = retrievePool(cost);
        if (curPool == null) {
            return false;
        }
        if (curPool.tryToRemoveSide(sideToRemove)) {
            return cardAlreadyPlayed.add(sideToRemove);
        }
        return false;
    }

    /**
     * retrieve the right pool based on the cost
     * 
     * @param cost
     * @return the pool
     */
    private Pool retrievePool(int cost) {
        for (Pool p : sidesAvailable) {
            if (p.getCost() == cost) {
                return p;
            }
        }
        return null;
    }
}