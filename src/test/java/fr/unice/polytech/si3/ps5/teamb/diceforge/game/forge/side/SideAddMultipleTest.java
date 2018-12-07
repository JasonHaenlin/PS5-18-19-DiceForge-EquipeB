package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.Assert.assertEquals;

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
}