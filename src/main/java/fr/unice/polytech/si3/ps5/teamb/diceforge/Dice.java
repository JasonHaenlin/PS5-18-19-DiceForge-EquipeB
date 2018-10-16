package fr.unice.polytech.si3.ps5.teamb.diceforge;

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

class Dice {

    private List<DiceSide> diceSides = new ArrayList<>();

    private int size;
    private Random rnd;
    private int currentRnd = 0;
    private DiceSide side;

    /**
     * Create a dice with 2 sides with MS
     *                    2 sides with SS
     *                    1 side with gold
     *                    1 side with PG
     */
    Dice() {
        rnd = new Random();
        for (int i = 0; i < 2; i++) {
            diceSides.add(new DiceSide(i, Resources.SS));
            diceSides.add(new DiceSide(i, Resources.MS));
        }
        diceSides.add(new DiceSide(2, Resources.G));
        diceSides.add(new DiceSide(1, Resources.PG));
        this.size = diceSides.size();
    }

    Dice(List<DiceSide> Side) {
        rnd = new Random();
        for (DiceSide s : Side) {
            diceSides.add(s);
        }
        this.size = diceSides.size();
    }

    /**
     *
     * Rolls Dice
     */
    void random() {
        currentRnd = rnd.nextInt(size);
        side = diceSides.get(currentRnd);
    }

    /**
     *
     * Get value after Rolls Dice
     */
    int getRandomValue(){
        return side.getValue();
    }

    Resources getRandomResources(){
        return side.getType();
    }

    /**
     *
     * @return the type of ressource of throw of the dice
     */
    String diceFaceType() {
        return diceSides.get(currentRnd).getType().toString();
    }

}
