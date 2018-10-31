package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Create the forge (actually forge only Gold Sides) We don't consider the
 * quantity of DiceSide (it's unlimited)
 *
 * @author UNG Vincent
 * @author HAENLIN Jason
 * @author HOURI Ruben
 * @author CASTELLANO Maxime
 *
 */
public class ActionForge {

    private Map<Integer, List<DiceSide>> sideGoldAvailable;
    // The key is the cost and the second param is side

    public ActionForge(Map<Integer, List<DiceSide>> sidesideGoldAvailable) {
        this.sideGoldAvailable = sidesideGoldAvailable;
    }

    public ActionForge() {
        sideGoldAvailable.put(3, Arrays.asList(new DiceSide(2, Resources.GOLD), new DiceSide(2, Resources.GOLD)));
        sideGoldAvailable.put(4, Arrays.asList(new DiceSide(3, Resources.GOLD), new DiceSide(3, Resources.GOLD)));
        sideGoldAvailable.put(5, Arrays.asList(new DiceSide(4, Resources.GOLD), new DiceSide(4, Resources.GOLD)));
    }

    public List<DiceSide> forgeAvailable(int cost) {
        return sideGoldAvailable.get(cost);
    }

    public boolean removeSide(DiceSide sideToRemove, int cost) {
        List<DiceSide> side = sideGoldAvailable.get(cost);
        for (int i = 0, n = side.size(); i < n; i++) {
            if (side.get(i).equals(sideToRemove)) {
                sideGoldAvailable.get(cost).remove(i);
                return true;
            }
        }
        return false;
    }

}
