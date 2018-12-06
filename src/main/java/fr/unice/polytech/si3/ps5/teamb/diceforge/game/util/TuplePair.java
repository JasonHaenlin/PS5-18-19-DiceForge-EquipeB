package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

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

}