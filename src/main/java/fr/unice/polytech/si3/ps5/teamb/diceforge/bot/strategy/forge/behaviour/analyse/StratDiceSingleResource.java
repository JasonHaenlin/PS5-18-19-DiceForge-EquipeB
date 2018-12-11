package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;

/**
 * StratDiceSingleResource is a block of strategy choosing the side to remove
 *
 * 
 * @see StratDice
 */
public class StratDiceSingleResource implements StratDice {

    @Override
    public int chooseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources) {
        if (resources == null)
            return 0;
        int potentialOfDice1 = 0;
        int potentialOfDice2 = 0;
        for (DiceSide side : diceSides0) {
            if (side.contains(resources))
                potentialOfDice1++;
        }
        for (DiceSide side : diceSides1) {
            if (side.contains(resources))
                potentialOfDice2++;
        }
        return potentialOfDice1 > potentialOfDice2 ? 0 : 1;
    }

    @Override
    public DiceSide chooseSideRemove(List<DiceSide> dicesSides, Resources resources) {
        List<DiceSide> potentielSides = new ArrayList<>();
        for (DiceSide side : dicesSides) {
            if (side.contains(Resources.GOLD)) {
                potentielSides.add(side);
            }
        }
        if (potentielSides.isEmpty())
            return null;
        for (int i = 0; i <= 6; i++) { // Récupère la valeur min
            for (DiceSide side : potentielSides) {
                if (side.coefficient() == i)
                    return side;
            }
        }
        return null;
    }

}