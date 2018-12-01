package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Tuple, most difficult class I have ever made
 * 
 * @author Jason Haenlin
 */

public class Tuple {
    public final Integer amount;
    public final Integer maximum;
    public final Resources resource;

    public Tuple(Resources resource, Integer amount, Integer maximum) {
        this.resource = resource;
        this.amount = amount;
        this.maximum = maximum;
    }

    public int delta() {
        return maximum - amount;
    }

    public int delta(int offset) {
        return (maximum - amount) - offset;
    }
}
