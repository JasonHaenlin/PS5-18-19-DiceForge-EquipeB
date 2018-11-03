package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, DiceSide> sideGoldAvailable;
    // The key is the cost and the second param is side

    public ActionForge(Map<Integer, DiceSide> sides) {
        sideGoldAvailable = new HashMap<>(sides);
    }

    public ActionForge() {
        Map<Integer, DiceSide> sides = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            DiceSide side = new DiceSide(i, Resources.GOLD);
            sides.put(i, side);
        }
        sideGoldAvailable = new HashMap<>(sides);
    }

    public List<DiceSide> forgeAvailable(int cost) {
        List<DiceSide> available = new ArrayList<>();
        for (int i = 1; i <= cost; i++) {
            if (sideGoldAvailable.containsKey(i)) {
                available.add(sideGoldAvailable.get(i));
            }
        }
        return available;
    }

}
