package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static Logger logger = LogManager.getLogger(Dice.class);

    private List<DiceSide> diceSides = new ArrayList<>();

    private int size;
    private Random rnd;

    /**
     * Create a dice with 2 sides with MS 2 sides with SS 1 side with gold 1 side
     * with PG
     */
    public Dice() {
        rnd = new Random();
        for (int i = 1; i < 3; i++) {
            diceSides.add(new DiceSide(i, Resources.SUN_STONE));
            diceSides.add(new DiceSide(i, Resources.MOON_STONE));
        }
        diceSides.add(new DiceSide(2, Resources.GOLD));
        diceSides.add(new DiceSide(1, Resources.VICTORY_POINT));
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
     * @param treasury
     */
    public DiceSide random() {
        logger.trace(diceSides.toString());
        return diceSides.get(rnd.nextInt(size));
    }

    public List<DiceSide> getDiceSides() {
        return diceSides;
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
