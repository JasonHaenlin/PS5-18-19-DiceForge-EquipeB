package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.util.Objects;

/**
 * TuplePair
 * 
 * Simple but usefull
 */
public class TuplePair<T, V> {

    public final T type;
    public final V value;

    public TuplePair(T type, V value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + type + ":" + value + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TuplePair<?, ?>)) {
            return false;
        }
        TuplePair<?, ?> side = (TuplePair<?, ?>) obj;
        return this.hashCode() == side.hashCode();
    }

    public static <T, V> TuplePair<T, V> emptySet() {
        return new TuplePair<T, V>(null, null);
    }

}