package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;

/**
 * HighestForgeTest
 */
public class HighestForgeTest {

    StratForge strF;

    @Before
    public void setup() {
        strF = new StratForgeHighestForge();
    }

    @Test
    public void HighesTest() {
        List<DiceSide> sides = new ArrayList<>();

        sides.add(new SideSimple(Resources.VICTORY_POINT, 5, 0));
        sides.add(new SideSimple(Resources.MOON_STONE, 5, 0));
        sides.add(new SideSimple(Resources.SUN_STONE, 5, 0));
        sides.add(new SideSimple(Resources.VICTORY_POINT, 5, 0));
        sides.add(new SideSimple(Resources.MOON_STONE, 5, 0));
        sides.add(new SideSimple(Resources.SUN_STONE, 5, 0));

        strF.execution(sides, Resources.MOON_STONE);
        assertEquals(2, sides.size());
        strF.execution(sides, Resources.GOLD);
        assertEquals(0, sides.size());
    }
}