package teamB.otake.diceForge;

import java.util.ArrayList;
import java.util.HashMap;
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


public class Dice {

    private List<DiceSide> diceSides = new ArrayList<>();

    private int size;
    Random rnd;

    public Dice(){
        rnd = new Random();
        for(int i = 0; i < 6; i++){
            DiceSide side = new DiceSide(i, Resources.PG);
            diceSides.add(side);
        }
        this.size = diceSides.size();
    }

    public Dice(HashMap<String, DiceSide> Side) {
        rnd = new Random();
        for (int i = 0; i < Side.size(); i++) {
            diceSides.add(Side.get("f" + i));
        }
        this.size = diceSides.size();
    }

    /**
     *
     * @return the value of throw of the dice
     */
    public int random() {
        return diceSides.get(rnd.nextInt(size)).getValue();
    }

    /**
     *
     * @return the type of ressource of throw of the dice
     */
    public String diceFaceType() {
        return diceSides.get(rnd.nextInt(size)).getType().toString();
    }

}
