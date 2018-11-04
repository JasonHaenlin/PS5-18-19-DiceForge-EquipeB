package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.Objects;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */
public class DiceSide {
    private int value;
    private Resources type;
    private int cost;

    public DiceSide(int value, Resources type) {
        this.value = value;
        this.type = type;
        this.cost = 0;
    }

    public DiceSide(int value, Resources type, int cost) {
        this.value = value;
        this.type = type;
        this.cost = cost;
    }

    /**
     * @return the type
     */
    public Resources getType() {
        return type;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "[" + value + "," + type + "]";
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
        return Objects.hash(this.value, this.type.ordinal());
    }
}
