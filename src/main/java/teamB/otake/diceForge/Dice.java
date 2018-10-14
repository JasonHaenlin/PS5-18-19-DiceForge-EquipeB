package teamb.otake.diceforge;

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

    Dice() {
        rnd = new Random();
        for (int i = 0; i < 6; i++) {
            diceSides.add(new DiceSide(i, Resources.PG));
        }
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
     * @return the value of throw of the dice
     */
    int random() {
        currentRnd = rnd.nextInt(size);
        return diceSides.get(currentRnd).getValue();
    }

    /**
     *
     * @return the type of ressource of throw of the dice
     */
    String diceFaceType() {
        return diceSides.get(currentRnd).getType().toString();
    }

}
