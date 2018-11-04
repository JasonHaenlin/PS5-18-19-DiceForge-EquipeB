package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

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

    public ActionForge() {
        sidesAvailable.put(3, Arrays.asList(new DiceSide(2, Resources.GOLD), new DiceSide(2, Resources.GOLD)));
        sidesAvailable.put(4, Arrays.asList(new DiceSide(3, Resources.GOLD), new DiceSide(3, Resources.GOLD)));
        sidesAvailable.put(5, Arrays.asList(new DiceSide(4, Resources.GOLD), new DiceSide(4, Resources.GOLD)));
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

    public boolean removeSide(DiceSide sideToRemove, int cost) {
        List<DiceSide> side = sidesAvailable.get(cost);
        for (int i = 0, n = side.size(); i < n; i++) {
            if (side.get(i).equals(sideToRemove)) {
                sidesAvailable.get(cost).remove(i);
                return true;
            }
        }
        return false;
    }

}
