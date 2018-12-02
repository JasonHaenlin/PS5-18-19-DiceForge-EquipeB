package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Create a dice
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 *
 */

public final class Dice {

    private final List<DiceSide> diceSides;
    private Random rnd;

    private int size;


    /**
     * Creates an empty dice which will be initialized
     * by the configuration file at the initialization of the game)
     */
    public Dice() {
        this.rnd = new Random();
        this.diceSides = new ArrayList<>();
    }

    /**
     * Create new dice
     */
    public Dice(List<DiceSide> side) {
        this();
        for (DiceSide s : side) {
            diceSides.add(s);
        }
        size = diceSides.size();
    }

    /**
     * Rolls Dice
     *
     */
    public DiceSide roll() {
        return diceSides.get(rnd.nextInt(size));
    }

    public List<DiceSide> retrieveCurrentSides() {
        return diceSides;
    }

    public List<DiceSide> getDiceSides() {
        return new ArrayList<>(diceSides);
    }

    public int size() {
        return diceSides.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dice)) {
            return false;
        }
        Dice dice = (Dice) obj;
        return this.hashCode() == dice.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (DiceSide side : diceSides) {
            hash += side.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return diceSides.toString();
    }

}
