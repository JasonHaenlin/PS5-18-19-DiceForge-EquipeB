package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */
public class DiceSide {
    protected int value;
    protected Resources type;

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DiceSide)) {
            return false;
        }
        DiceSide diceSide = (DiceSide) obj;
        return this.type == diceSide.type && this.value == diceSide.value;
    }

}
