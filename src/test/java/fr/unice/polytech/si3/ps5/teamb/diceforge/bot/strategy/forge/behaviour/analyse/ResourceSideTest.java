package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;

/**
 * ResourceSideTest
 */
public class ResourceSideTest {

    StratDice strD;

    @Before
    public void setup() {
        strD = new StratDiceResourceSide();
    }

    @Test
    public void test() {
        List<DiceSide> dice0 = new ArrayList<>();
        dice0.add(new SideSimple(Resources.VICTORY_POINT, 5, 0));
        dice0.add(new SideSimple(Resources.MOON_STONE, 5, 0));
        dice0.add(new SideSimple(Resources.SUN_STONE, 5, 0));

        List<DiceSide> dice1 = new ArrayList<>();
        dice1.add(new SideSimple(Resources.VICTORY_POINT, 5, 0));
        dice1.add(new SideSimple(Resources.MOON_STONE, 5, 0));
        dice1.add(new SideSimple(Resources.SUN_STONE, 5, 0));

        int numberDice = strD.chooseDice(dice0, dice1, null);
        List<DiceSide> dice = numberDice == 0 ? dice0 : dice1;

        DiceSide removable = strD.chooseSideRemove(dice, null);
        assertTrue(removable.contains(Resources.VICTORY_POINT));

        List<DiceSide> dice2 = new ArrayList<>();
        dice2.add(new SideSimple(Resources.GOLD, 5, 0));
        dice2.add(new SideSimple(Resources.MOON_STONE, 5, 0));
        dice2.add(new SideSimple(Resources.SUN_STONE, 5, 0));

        List<DiceSide> dice3 = new ArrayList<>();
        dice3.add(new SideSimple(Resources.GOLD, 5, 0));
        dice3.add(new SideSimple(Resources.MOON_STONE, 5, 0));
        dice3.add(new SideSimple(Resources.SUN_STONE, 5, 0));

        strD.chooseDice(dice2, dice3, null);
        List<DiceSide> dice5 = numberDice == 0 ? dice2 : dice3;

        DiceSide removable2 = strD.chooseSideRemove(dice5, null);
        assertTrue(removable2.contains(Resources.GOLD));

    }
}