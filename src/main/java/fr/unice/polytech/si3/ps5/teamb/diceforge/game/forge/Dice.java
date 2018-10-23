package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    DiceSide side;

    /**
     * Create a dice with 2 sides with MS 2 sides with SS 1 side with gold 1 side
     * with PG
     */
    public Dice() {
        rnd = new Random();
        for (int i = 0; i < 2; i++) {
            diceSides.add(new DiceSide(i, Resources.SUN_STUNE));
            diceSides.add(new DiceSide(i, Resources.MOON_STONE));
        }
        diceSides.add(new DiceSide(2, Resources.GOLD));
        diceSides.add(new DiceSide(1, Resources.VICTORY_POINT));
        this.size = diceSides.size();
    }

    public Dice(List<DiceSide> Side) {
        rnd = new Random();
        for (DiceSide s : Side) {
            diceSides.add(s);
        }
        this.size = diceSides.size();
    }

    /**
     *
     * Rolls Dice
     * 
     * @param treasury
     */
    public void random(Map<Resources, Integer> treasury) {
        side = diceSides.get(rnd.nextInt(size));
        treasury.replace(side.getType(), side.getValue() + treasury.get(side.getType()));
    }

    int getRandomValue() {
        return side.getValue();
    }

    Resources getRandomResources() {
        return side.getType();
    }
}
