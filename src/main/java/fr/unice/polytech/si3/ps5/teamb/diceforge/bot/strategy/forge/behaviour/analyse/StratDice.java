package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * StratDice
 * 
 */
public interface StratDice {

    /**
     * Chooses the best dice considering the resource you want to add
     * 
     * @param diceSides0 first dice
     * @param diceSides1 second dice
     * @param resources  the resource you are looking for
     * @return the best dice
     */
    int chooseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources);

    /**
     * chose the best dice side best on all the sides and the resource you want to
     * replace
     * 
     * @param dicesSides list from your dice
     * @param resources  you want to add on the dice
     * @return the right side
     */
    DiceSide chooseSideRemove(List<DiceSide> dicesSides, Resources resources);

}