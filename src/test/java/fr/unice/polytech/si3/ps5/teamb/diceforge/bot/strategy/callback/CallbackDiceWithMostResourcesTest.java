package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.HelperPlayer;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * CallbackDiceWithMostResourcesTest
 */
public class CallbackDiceWithMostResourcesTest {
    Callback<Integer, Resources> c;
    Manager m;

    @Before
    public void setup() {
        c = new CallbackDiceWithMostResources();
        m = new Manager(HelperPlayer.first);
    }

    @Test
    public void callbackDiceWithMostResourcesTest() {
        int id = c.runCallback(m.getContext(), Resources.MOON_STONE);
        assertEquals(0, id);
        id = c.runCallback(m.getContext(), Resources.SUN_STONE);
        assertEquals(1, id);
        id = c.runCallback(m.getContext(), Resources.VICTORY_POINT);
        assertEquals(0, id);
        id = c.runCallback(m.getContext(), Resources.GOLD);
        assertEquals(1, id);
    }
}