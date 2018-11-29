package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * ResourceSideTest
 */
public class ResourceSideTest {

    StratDice strD;

    @Before
    public void setup() {
        strD = new ResourceSide();
    }

    @Test
    public void test() {
        List<DiceSide> dice0 = new ArrayList<>();
        dice0.add(new DiceSide(5, Resources.VICTORY_POINT));
        dice0.add(new DiceSide(5, Resources.MOON_STONE));
        dice0.add(new DiceSide(5, Resources.SUN_STONE));

        List<DiceSide> dice1 = new ArrayList<>();
        dice1.add(new DiceSide(5, Resources.VICTORY_POINT));
        dice1.add(new DiceSide(5, Resources.MOON_STONE));
        dice1.add(new DiceSide(5, Resources.SUN_STONE));

        int numberDice = strD.choseDice(dice0, dice1, null);
        List<DiceSide> dice = numberDice == 0 ? dice0 : dice1;

        DiceSide removable = strD.choseSideRemove(dice, null);
        assertEquals(Resources.VICTORY_POINT, removable.getType());

        List<DiceSide> dice2 = new ArrayList<>();
        dice2.add(new DiceSide(5, Resources.GOLD));
        dice2.add(new DiceSide(5, Resources.MOON_STONE));
        dice2.add(new DiceSide(5, Resources.SUN_STONE));

        List<DiceSide> dice3 = new ArrayList<>();
        dice3.add(new DiceSide(5, Resources.GOLD));
        dice3.add(new DiceSide(5, Resources.MOON_STONE));
        dice3.add(new DiceSide(5, Resources.SUN_STONE));

        strD.choseDice(dice2, dice3, null);
        List<DiceSide> dice5 = numberDice == 0 ? dice2 : dice3;

        DiceSide removable2 = strD.choseSideRemove(dice5, null);
        assertEquals(Resources.GOLD, removable2.getType());

    }
}