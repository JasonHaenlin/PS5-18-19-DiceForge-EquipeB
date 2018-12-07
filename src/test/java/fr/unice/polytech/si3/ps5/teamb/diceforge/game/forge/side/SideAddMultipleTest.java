package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.Instructions;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideAddMultiple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideAddMultipleTest
 */
public class SideAddMultipleTest {

    @Test
    public void sideAddMultipleTest() {
        List<TuplePair<Resources, Integer>> sides = new ArrayList<>();
        sides.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));

        DiceSide side = new SideAddMultiple(sides, 5);

        List<Instructions> inst = side.getInstructions();
        assertEquals(2, inst.size());
    }

    @Test
    public void sideAddMultipleEqualsTest() {
        List<TuplePair<Resources, Integer>> sides1 = new ArrayList<>();
        sides1.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides1.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side1 = new SideAddMultiple(sides1, 5);

        List<TuplePair<Resources, Integer>> sides2 = new ArrayList<>();
        sides2.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides2.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side2 = new SideAddMultiple(sides2, 5);

        List<TuplePair<Resources, Integer>> sides3 = new ArrayList<>();
        sides3.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides3.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        sides3.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side3 = new SideAddMultiple(sides3, 5);

        assertTrue(side1.equals(side2));
        assertFalse(side3.equals(side2));
    }

    @Test
    public void sideAddMultipleCoefTest() {
        List<TuplePair<Resources, Integer>> sides1 = new ArrayList<>();
        sides1.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides1.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side1 = new SideAddMultiple(sides1, 5);

        assertEquals(7, side1.coefficient());
    }
}