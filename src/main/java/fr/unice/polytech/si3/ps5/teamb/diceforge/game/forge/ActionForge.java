package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Create the forge
 *
 * @author UNG Vincent
 * @author HAENLIN Jason
 * @author HOURI Ruben
 * @author CASTELLANO Maxime
 *
 */
public class ActionForge {

    private Map<Integer, List<DiceSide>> sidesAvailable;

    public ActionForge(Map<Integer, List<DiceSide>> sides) {
        sidesAvailable = new HashMap<>(sides);
    }

    public List<DiceSide> availableSides(int cost) {
        List<DiceSide> available = new ArrayList<>();
        for (int i = 1; i <= cost; i++) {
            if (sidesAvailable.containsKey(i)) {
                available.addAll(sidesAvailable.get(i));
            }
        }
        return available;
    }

    public boolean removeSide(DiceSide sideToRemove) {
        int cost = sideToRemove.getCost();
        List<DiceSide> side = sidesAvailable.get(cost);
        if (side == null) {
            return false;
        }
        for (int i = 0, n = side.size(); i < n; i++) {
            if (side.get(i).equals(sideToRemove)) {
                sidesAvailable.get(cost).remove(i);
                return true;
            }
        }
        return false;
    }

}
