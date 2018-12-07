package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.Instructions;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideSimpleTest
 */
public class SideSimpleTest {

    @Test
    public void simpleTest() {
        DiceSide side1 = new SideSimple(Resources.GOLD, 5, 3);
        List<Instructions> inst = side1.getInstructions();

        assertEquals(1, inst.size());

        TuplePair<Resources, Integer> t = inst.get(0).execution(null, null);
        assertEquals(5, (int) t.value);
        assertTrue(t.type.equals(Resources.GOLD));
    }

}