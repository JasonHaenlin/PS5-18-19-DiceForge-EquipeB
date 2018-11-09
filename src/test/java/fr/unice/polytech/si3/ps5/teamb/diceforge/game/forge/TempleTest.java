package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * TempleTest
 */
public class TempleTest {

    Temple temple;
    List<Pool> pool;
    Map<Integer, List<DiceSide>> temp;
    DiceSide side1 = new DiceSide(1, Resources.GOLD, 3);
    DiceSide side2 = new DiceSide(1, Resources.GOLD, 4);
    DiceSide side3 = new DiceSide(1, Resources.GOLD, 5);

    @Before
    public void setup() {

        temp = new HashMap<>();
        temp.put(3, new ArrayList<>(Arrays.asList(side1, side1, side1)));
        temp.put(4, new ArrayList<>(Arrays.asList(side2, side2, side2)));
        temp.put(5, new ArrayList<>(Arrays.asList(side3, side3, side3)));
    }

    @Test
    public void AvailableSideTest() {
        temple = new Temple(temp);
        assertEquals(3, temple.availableSides(3).size());
        assertEquals(6, temple.availableSides(4).size());
        assertEquals(9, temple.availableSides(5).size());
    }

    @Test
    public void removeSideTest() {
        temple = new Temple(temp);
        assertTrue(temple.removeSide(side1));
        assertEquals(2, temple.availableSides(3).size());
        assertTrue(temple.removeSide(side2));
        assertEquals(7, temple.availableSides(5).size());
        assertTrue(temple.removeSide(side3));
        assertEquals(6, temple.availableSides(5).size());
    }
}