package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

public class ForgeTest {
    private Forge forge;

    @Before
    public void setup() {
        forge = new VictoryPoint("BotTest");

    }

    @Test
    public void test() {
        List<DiceSide> dice0 = new ArrayList<>();
        dice0.add(new DiceSide(5, Resources.VICTORY_POINT));
        dice0.add(new DiceSide(5, Resources.MOON_STONE));
        dice0.add(new DiceSide(5, Resources.SUN_STONE));

        List<DiceSide> dice1 = new ArrayList<>();
        dice1.add(new DiceSide(5, Resources.VICTORY_POINT));
        dice1.add(new DiceSide(5, Resources.MOON_STONE));
        dice1.add(new DiceSide(5, Resources.SUN_STONE));

        DiceSide removable = forge.removableDiceSide(dice0, dice1, Resources.VICTORY_POINT);
        assertEquals(Resources.VICTORY_POINT, removable.getType());

        List<DiceSide> dice2 = new ArrayList<>();
        dice2.add(new DiceSide(5, Resources.GOLD));
        dice2.add(new DiceSide(5, Resources.MOON_STONE));
        dice2.add(new DiceSide(5, Resources.SUN_STONE));

        List<DiceSide> dice3 = new ArrayList<>();
        dice3.add(new DiceSide(5, Resources.GOLD));
        dice3.add(new DiceSide(5, Resources.MOON_STONE));
        dice3.add(new DiceSide(5, Resources.SUN_STONE));

        DiceSide removable2 = forge.removableDiceSide(dice2, dice3, Resources.VICTORY_POINT);
        assertEquals(Resources.GOLD, removable2.getType());

    }
}