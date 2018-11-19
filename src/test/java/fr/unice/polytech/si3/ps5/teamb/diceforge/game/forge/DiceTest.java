package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

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
            diceSidesGold.add(new DiceSide(1, Resources.GOLD));
            diceSidesSunStone.add((new DiceSide(3, Resources.SUN_STONE)));
        }

        diceGold = new Dice(diceSidesGold);
        diceSun = new Dice(diceSidesSunStone);
    }

    @Test
    public void test() {
        DiceSide gold = diceGold.roll();
        assertEquals(1, gold.getValue());
        assertEquals(Resources.GOLD, gold.getType());

        DiceSide sun = diceSun.roll();
        assertEquals(3, sun.getValue());
        assertEquals(Resources.SUN_STONE, sun.getType());
    }
}