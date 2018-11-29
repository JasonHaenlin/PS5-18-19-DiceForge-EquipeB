package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * HighestForgeTest
 */
public class HighestForgeTest {

    StratForge strF;

    @Before
    public void setup() {
        strF = new HighestForge();
    }

    @Test
    public void HighesTest() {
        List<DiceSide> sides = new ArrayList<>();

        sides.add(new DiceSide(5, Resources.VICTORY_POINT));
        sides.add(new DiceSide(5, Resources.MOON_STONE));
        sides.add(new DiceSide(5, Resources.SUN_STONE));
        sides.add(new DiceSide(5, Resources.VICTORY_POINT));
        sides.add(new DiceSide(5, Resources.MOON_STONE));
        sides.add(new DiceSide(5, Resources.SUN_STONE));

        strF.execution(sides, Resources.MOON_STONE);
        assertEquals(2, sides.size());
        strF.execution(sides, Resources.GOLD);
        assertEquals(0, sides.size());
    }
}