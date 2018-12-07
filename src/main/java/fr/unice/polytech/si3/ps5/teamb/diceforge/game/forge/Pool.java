package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;

/**
 * Pool
 */
public class Pool {

    private final List<DiceSide> sides;
    private int cost;

    /**
     * create a pool of sides
     */
    public Pool(List<DiceSide> sides, int cost) {
        this.sides = new ArrayList<>(sides);
        this.cost = cost;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the sides
     */
    public List<DiceSide> getSides() {
        return sides;
    }

    public boolean tryToRemoveSide(DiceSide sideToRemove) {
        return sides.remove(sideToRemove);
    }

    @Override
    public String toString() {
        return "[" + cost + " => " + sides.toString() + "]";
    }

}