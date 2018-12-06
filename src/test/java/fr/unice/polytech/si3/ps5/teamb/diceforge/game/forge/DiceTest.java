package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;

/**
 * InventoryTest
 */
public class DiceTest {

    Dice diceGold;
    Dice diceSun;

    @Before
    public void setup() {
        List<DiceSide> diceSidesGold = new ArrayList<>();
        List<DiceSide> diceSidesSunStone = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            diceSidesGold.add(new SideSimple(Resources.GOLD, 1, 0));
            diceSidesSunStone.add(new SideSimple(Resources.SUN_STONE, 3, 0));
        }

        diceGold = new Dice(diceSidesGold);
        diceSun = new Dice(diceSidesSunStone);
    }

    @Test
    public void test() {
        DiceSide gold = diceGold.roll();
        assertEquals(1, gold.coefficient());
        assertTrue(gold.contains(Resources.GOLD));

        DiceSide sun = diceSun.roll();

        assertEquals(3, sun.coefficient());
        assertTrue(sun.contains(Resources.SUN_STONE));
    }
}