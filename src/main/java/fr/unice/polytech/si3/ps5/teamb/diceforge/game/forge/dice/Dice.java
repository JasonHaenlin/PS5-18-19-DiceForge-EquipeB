package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

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

public class Dice {

    private List<DiceSide> diceSides = new ArrayList<>();

    private int size;
    private Random rnd;

    /**
     * Creates a 6 sides dice with a side with 2 victory points, a side with 1
     * moon stones, a side with 1 sun stone and 3 sides with 1 gold (will be changed
     * by the configuration file at the initialization of the game)
     */
    public Dice() {
        rnd = new Random();
        diceSides.add(new DiceSide(2, Resources.VICTORY_POINT));
        diceSides.add(new DiceSide(1, Resources.SUN_STONE));
        diceSides.add(new DiceSide(1, Resources.MOON_STONE));
        diceSides.add(new DiceSide(1, Resources.GOLD));
        diceSides.add(new DiceSide(1, Resources.GOLD));
        diceSides.add(new DiceSide(1, Resources.GOLD));
        this.size = diceSides.size();
    }

    public Dice(List<DiceSide> side) {
        rnd = new Random();
        for (DiceSide s : side) {
            diceSides.add(s);
        }
        this.size = diceSides.size();
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
