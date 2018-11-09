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

    private void putInTemple(Map<Integer, List<DiceSide>> sides) {
        this.sidesAvailable = new ArrayList<>();
        sides.forEach((cost, side) -> this.sidesAvailable.add(new Pool(side, cost)));
    }

    public List<DiceSide> availableSides(int cost) {
        List<DiceSide> available = new ArrayList<>();
        for (int i = 1; i <= cost; i++) {
            Pool p = RetrievePool(i);
            if (p != null) {
                available.addAll(p.getSides());
            }
        }
        return available;
    }

    public boolean removeSide(DiceSide sideToRemove) {
        int cost = sideToRemove.getCost();
        Pool curPool = RetrievePool(cost);
        if (curPool == null) {
            System.out.println("null");
            return false;
        }
        return curPool.tryToRemoveSide(sideToRemove);
    }

    private Pool RetrievePool(int cost) {
        for (Pool p : sidesAvailable) {
            if (p.getCost() == cost) {
                return p;
            }
        }
        return null;
    }
}