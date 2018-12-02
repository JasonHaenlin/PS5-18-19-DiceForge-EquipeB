package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

/**
 * Tuple, most difficult class I have ever made. The Tuple is made to store int
 * value and optionnaly store a type
 * 
 * @author Jason Haenlin
 */

public class Tuple<TYPE> {
    public final TYPE type;
    public final int maximum;
    public int value;

    public Tuple(TYPE type, int value, int maximum) {
        this.type = type;
        this.value = value;
        this.maximum = maximum;
    }

    public int delta() {
        return maximum - value;
    }

    public int delta(int offset) {
        return (maximum - value) - offset;
    }

    public int incrementAndGet() {
        value++;
        return value;
    }

    @Override
    public String toString() {
        return "[" + type + ": (" + value + " / " + maximum + ")" + "]";
    }

}
