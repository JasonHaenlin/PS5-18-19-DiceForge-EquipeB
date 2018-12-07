package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.Assert.assertEquals;

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
}