package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

/**
 * Tuple, most difficult class I have ever made. The Tuple is made to store int
 * value and optionnaly store a type
 * 
 * The type can be a descriptor of the stored values.
 * 
 * @author Jason Haenlin
 */

public class TupleInteger<T> {
    private final T type;
    private final int maximum;

    private int value;

    /**
     * Provide a way to store a value, a maximum value and a descriptor for the
     * value
     * 
     * @param type    Type or descripteur of the Tuple
     * @param value   value of the Tuple
     * @param maximum value
     */
    public TupleInteger(T type, int value, int maximum) {
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

    /**
     * 
     * @return the value + 1
     */
    public int incrementAndGet() {
        value++;
        return value;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the type
     */
    public T getType() {
        return type;
    }

    /**
     * @return the maximum
     */
    public int getMaximum() {
        return maximum;
    }

    @Override
    public String toString() {
        return "[" + type.toString() + ": (" + value + " / " + maximum + ")" + "]";
    }

}
