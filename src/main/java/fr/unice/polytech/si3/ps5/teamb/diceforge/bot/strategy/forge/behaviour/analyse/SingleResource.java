package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * SingleResource
 * 
 * @see StratDice
 */
public class SingleResource implements StratDice {

    int c1;
    int c2;

    @Override
    public int choseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources) {
        if (resources == null)
            return 0;
        c1 = 0;
        c2 = 0;
        for (DiceSide side : diceSides0) {
            if (side.getType().equals(resources))
                c1++;
        }
        for (DiceSide side : diceSides1) {
            if (side.getType().equals(resources))
                c2++;
        }
        return c1 > c2 ? 0 : 1;
    }

    @Override
    public DiceSide choseSideRemove(List<DiceSide> dicesSides, Resources resources) {
        List<DiceSide> potentielSides = new ArrayList<>();
        for (DiceSide side : dicesSides) {
            if (side.getType() == Resources.GOLD) {
                potentielSides.add(side);
            }
        }
        if (potentielSides.isEmpty())
            return null;
        for (int i = 0; i <= 6; i++) { // Récupère la valeur min
            for (DiceSide side : potentielSides) {
                if (side.getValue() == i)
                    return side;
            }
        }
        return null;
    }

}