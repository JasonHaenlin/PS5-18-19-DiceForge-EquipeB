package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

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
        return diceSides.get(rnd.nextInt(size));
    }

    public List<DiceSide> getDiceSides(){return diceSides;}

    /**
     * To change the Dice, we need to know what side remove
     * and what side to add
     * Il faut rajouter un système de sécurité
     * @param sideRemove
     * @param sideAdd
     */
    public void setDiceSides(DiceSide sideRemove, DiceSide sideAdd){
        Resources res = sideRemove.getType();
        int value = sideRemove.getValue();

        List<Resources> listRes = new ArrayList<>();
        List<Integer> listValue = new ArrayList<>();
        for(DiceSide side : diceSides){
            listRes.add(side.getType());
            listValue.add(side.getValue());
        }
        if(listRes.contains(res) && listValue.contains((value))) {
            diceSides.remove(sideRemove);
            diceSides.add(sideAdd);
        }
    }
}
