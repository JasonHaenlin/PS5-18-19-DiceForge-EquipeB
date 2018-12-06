package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * instructions
 */
public interface Instructions {

    /**
     * create an instruction for the dice side.
     * 
     * @param secondary
     * @param player
     * @return
     */
    TuplePair<Resources, Integer> execution(DiceSide secondary, Player player);

    static List<TuplePair<Resources, Integer>> ExecuteAll(DiceSide primary, DiceSide secondary, Player player) {
        List<Instructions> inst = primary.getInstructions();
        List<TuplePair<Resources, Integer>> tuples = new ArrayList<>();
        for (Instructions it : inst) {
            tuples.add(it.execution(secondary, player));
        }
        return tuples;
    }

}