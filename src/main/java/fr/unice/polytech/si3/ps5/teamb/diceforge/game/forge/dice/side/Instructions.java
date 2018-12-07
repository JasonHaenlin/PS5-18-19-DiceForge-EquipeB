package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

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

}