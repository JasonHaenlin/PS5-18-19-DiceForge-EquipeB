package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * DiceSideBis
 */
public abstract class DiceSide {

    private final int cost;
    private final String name;
    private final List<Instructions> inst;

    public DiceSide(int cost, String name) {
        this.cost = cost;
        this.name = name;
        this.inst = new ArrayList<>();
    }

    public abstract void setAllInstrucion(List<Instructions> inst);

    public abstract boolean contains(Resources res);

    public abstract int coefficient();

    /**
     * @return the inst
     */
    public List<Instructions> getInstructions() {
        return inst;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DiceSide)) {
            return false;
        }
        DiceSide side = (DiceSide) obj;
        return this.hashCode() == side.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.cost);
    }

    public static DiceSide emptySide() {
        return new SideSimple(Resources.GOLD, 0, 0);
    }
}