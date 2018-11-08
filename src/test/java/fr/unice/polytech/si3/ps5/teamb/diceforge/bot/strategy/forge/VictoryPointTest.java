package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

public class VictoryPointTest {
    private VictoryPoint vPoint;

    @Before
    public void setup() {
        vPoint = new VictoryPoint("BotTest");

    }

    @Test
    public void test() {
        List<DiceSide> feasible = new ArrayList<>();
        feasible.add(new DiceSide(1, Resources.VICTORY_POINT));
        feasible.add(new DiceSide(2, Resources.VICTORY_POINT));
        feasible.add(new DiceSide(3, Resources.VICTORY_POINT));
        feasible.add(new DiceSide(4, Resources.VICTORY_POINT));
        feasible.add(new DiceSide(5, Resources.VICTORY_POINT));
        feasible.add(new DiceSide(5, Resources.VICTORY_POINT));
        feasible.add(new DiceSide(1, Resources.GOLD));
        feasible.add(new DiceSide(2, Resources.GOLD));
        feasible.add(new DiceSide(3, Resources.GOLD));
        feasible.add(new DiceSide(4, Resources.GOLD));
        feasible.add(new DiceSide(5, Resources.GOLD));
        feasible.add(new DiceSide(1, Resources.MOON_STONE));
        feasible.add(new DiceSide(2, Resources.MOON_STONE));
        feasible.add(new DiceSide(3, Resources.MOON_STONE));
        feasible.add(new DiceSide(4, Resources.MOON_STONE));
        feasible.add(new DiceSide(5, Resources.MOON_STONE));
        feasible.add(new DiceSide(1, Resources.SUN_STONE));
        feasible.add(new DiceSide(2, Resources.SUN_STONE));
        feasible.add(new DiceSide(3, Resources.SUN_STONE));
        feasible.add(new DiceSide(4, Resources.SUN_STONE));
        feasible.add(new DiceSide(5, Resources.SUN_STONE));
        DiceSide test = vPoint.compute(feasible);
        assertEquals(5, test.getValue());
        assertEquals(Resources.VICTORY_POINT, test.getType());

    }
}