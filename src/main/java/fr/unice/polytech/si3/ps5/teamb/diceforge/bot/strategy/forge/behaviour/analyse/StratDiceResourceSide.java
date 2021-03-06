package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import java.util.List;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;

/**
 * ResourceSide is a clock of strategy choosing randomly the dice to craft on and a side with gold or victory
 * point on it
 * 
 * @see StratDice
 */
public class StratDiceResourceSide implements StratDice {

    @Override
    public int chooseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources) {
        return new Random().nextInt(2);
    }

    @Override
    public DiceSide chooseSideRemove(List<DiceSide> dicesSides, Resources resources) {
        for (DiceSide diceside : dicesSides) {
            if (diceside.contains(Resources.GOLD) || diceside.contains(Resources.VICTORY_POINT)) {
                return diceside;
            }
        }
        return null;
    }

}