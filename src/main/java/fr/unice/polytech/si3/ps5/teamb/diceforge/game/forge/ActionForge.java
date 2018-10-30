package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Inventory;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create the forge
 * (actually forge only Gold Sides)
 * We don't consider the quantity of DiceSide
 * (it's unlimited)
 *
 * @author UNG Vincent
 * @author HAENLIN Jason
 * @author HOURI Ruben
 * @author CASTELLANO Maxime
 *
 */
public class ActionForge {

    private Map<Integer,DiceSide> sideGoldAvailable;
    private Inventory inventory;
    //The key is the cost and the second param is side

    ActionForge(Map<Integer,DiceSide> sides, Inventory inventory){
        sideGoldAvailable = new HashMap<>(sides);
        this.inventory = inventory; //Same reference
    }

    List<DiceSide> forgeAvailable(int cost) {
        List<DiceSide> available = new ArrayList<>();
        for (int i = 1; i <= cost; i++) {
            if (sideGoldAvailable.containsKey(i)) {
                available.add(sideGoldAvailable.get(i));
            }
        }
        return available;
    }

    /**
     * Il faut rajouter un système de securité
     * @param dice
     * @param sideForge
     * @param sideRemove
     * @param cost
     */
    void forge(Dice dice, DiceSide sideForge, DiceSide sideRemove, int cost){
        List<DiceSide> diceSides = dice.getDiceSides();
        //if(forgeAvailable(cost).contains(sideForge) && diceSides.contains(sideRemove)){
            dice.setDiceSides(sideRemove,sideForge);
            inventory.spendResources(Resources.GOLD,cost);
        //}
    }

}
