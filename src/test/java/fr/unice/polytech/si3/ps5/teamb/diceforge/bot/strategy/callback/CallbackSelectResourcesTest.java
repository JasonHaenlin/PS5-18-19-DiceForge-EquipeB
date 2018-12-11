package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.HelperPlayer;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * CallbackSelectResourcesTest
 */
public class CallbackSelectResourcesTest {

    Callback<Resources, Map<Resources, Integer>> c;
    Manager m;

    @Before
    public void setup() {
        c = new CallbackSelectResources();
        m = new Manager(HelperPlayer.first);
    }

    @Test
    public void callbackSelectResourcesTest() {
        Map<Resources, Integer> value = new HashMap<>();
        value.put(Resources.MOON_STONE, 15);
        value.put(Resources.SUN_STONE, 10);
        Resources res = c.runCallback(m.getContext(), value);
        assertTrue(Resources.SUN_STONE.equals(res));
    }
}