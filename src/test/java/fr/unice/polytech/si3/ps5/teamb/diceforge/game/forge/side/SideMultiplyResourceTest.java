package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideMultiplyResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideMultiplyResourceTest
 */
public class SideMultiplyResourceTest {

    @Test
    public void sideMultiplyResourceTest() {
        DiceSide side = new SideSimple(Resources.MOON_STONE, 5, 2);
        DiceSide sideToTest = new SideMultiplyResource(3);

        List<TuplePair<Resources, Integer>> inst = side.executeInstructions(sideToTest, null);
        inst.addAll(sideToTest.executeInstructions(side, null));
        assertEquals(4, inst.size());
        int count = 0;
        for (TuplePair<Resources, Integer> t : inst) {
            count += t.value;
        }
        assertEquals(15, count);
    }

    @Test
    public void sideMultiplyResourceReverseTest() {
        DiceSide side = new SideSimple(Resources.MOON_STONE, 5, 2);
        DiceSide sideToTest = new SideMultiplyResource(3);

        List<TuplePair<Resources, Integer>> inst = sideToTest.executeInstructions(side, null);
        inst.addAll(side.executeInstructions(sideToTest, null));
        assertEquals(4, inst.size());
        int count = 0;
        for (TuplePair<Resources, Integer> t : inst) {
            count += t.value;
        }
        assertEquals(15, count);
    }

    @Test
    public void sideMultiplyResourceEqualsTest() {
        DiceSide side1 = new SideMultiplyResource(3);
        DiceSide side2 = new SideMultiplyResource(3);
        DiceSide side3 = new SideMultiplyResource(5);

        assertTrue(side1.equals(side2));
        assertFalse(side3.equals(side2));
    }

    @Test
    public void sideMultiplyMultiplyTest() {
        DiceSide side1 = new SideMultiplyResource(0);
        DiceSide side2 = new SideMultiplyResource(0);
        List<TuplePair<Resources, Integer>> inst = new ArrayList<>();
        inst.addAll(side1.executeInstructions(side2, null));
        inst.addAll(side2.executeInstructions(side1, null));
        assertTrue(inst.size() == 2);
    }
}