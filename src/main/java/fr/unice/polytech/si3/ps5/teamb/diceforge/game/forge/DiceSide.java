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

    public DiceSide(int value, Resources type) {
        this.value = value;
        this.type = type;
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
