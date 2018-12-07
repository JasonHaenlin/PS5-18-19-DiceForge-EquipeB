package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.HelperPlayer;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.Instructions;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideChoice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideChoiceTest
 */
public class SideChoiceTest {

    @Test
    public void sideChoiceTest() {
        List<TuplePair<Resources, Integer>> sides = new ArrayList<>();
        sides.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side = new SideChoice(sides, 5);

        List<Instructions> inst = side.getInstructions();
        assertEquals(1, inst.size());

        TuplePair<Resources, Integer> t = inst.get(0).execution(null, HelperPlayer.first);

        assertEquals(Resources.GOLD, t.type);
    }

    @Test
    public void sideChoiceEqualsTest() {
        List<TuplePair<Resources, Integer>> sides1 = new ArrayList<>();
        sides1.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides1.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side1 = new SideChoice(sides1, 5);

        List<TuplePair<Resources, Integer>> sides2 = new ArrayList<>();
        sides2.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides2.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side2 = new SideChoice(sides2, 5);

        List<TuplePair<Resources, Integer>> sides3 = new ArrayList<>();
        sides3.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides3.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        sides3.add(new TuplePair<Resources, Integer>(Resources.SUN_STONE, 2));
        DiceSide side3 = new SideChoice(sides3, 5);

        assertTrue(side1.equals(side2));
        assertFalse(side3.equals(side2));
    }

    @Test
    public void sideChoiceCoefTest() {
        List<TuplePair<Resources, Integer>> sides1 = new ArrayList<>();
        sides1.add(new TuplePair<Resources, Integer>(Resources.GOLD, 5));
        sides1.add(new TuplePair<Resources, Integer>(Resources.MOON_STONE, 2));
        DiceSide side1 = new SideChoice(sides1, 5);

        assertEquals(3, side1.coefficient());
    }
}