package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create the forge
 * (actually forge only Gold Sides)
 *
 * @author UNG Vincent
 * @author HAENLIN Jason
 * @author HOURI Ruben
 * @author CASTELLANO Maxime
 *
 */
public class ForgeDiceSide {

    private Map<Integer,Integer> sideGoldAvailable;
    //The key is the cost and the second param is the value of side

    ForgeDiceSide(Map<Integer,Integer> sides){
        sideGoldAvailable = new HashMap<>(sides);
    }

    List<Integer> forgeAvailable(int cost) {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= cost; i++) {
            if (sideGoldAvailable.containsKey(i)) {
                available.add(sideGoldAvailable.get(i));
            }
        }
        return available;
    }

    void forge(Dice dice, DiceSide sideForge, DiceSide sideRemove, int cost){
        /* TODO */
            

    }
}
