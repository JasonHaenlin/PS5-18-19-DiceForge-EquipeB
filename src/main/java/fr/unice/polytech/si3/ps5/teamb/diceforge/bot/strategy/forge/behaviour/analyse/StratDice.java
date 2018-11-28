package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * StratDice
 */
public interface StratDice {

    int choseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources);

    DiceSide choseSideRemove(List<DiceSide> dicesSides, Resources resources);

}