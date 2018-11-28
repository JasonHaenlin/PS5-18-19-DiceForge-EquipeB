package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * SingleResource
 */
public class SingleResource implements StratDice {

    @Override
    public int choseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources) {
        // TODO
        return 0;
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