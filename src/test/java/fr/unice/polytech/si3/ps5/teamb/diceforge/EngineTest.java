package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.SimpleHighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.helper.ParseHelper;

/**
 * engineTest
 */
public class EngineTest {

    Engine engine;

    @Before
    public void setup() {
        engine = new Engine();
    }

    @Test
    public void engineResultTest1() throws Exception {
        String result = engine.createGame(1).addBot(OnlyDice.class).addBot(SimpleHighestExploit.class).fire();
        Map<String, String> res = ParseHelper.parseResult("onlyDice", "SimpleHighestExploit", result);
        assertTrue(res.get("SimpleHighestExploit").contains("gagne 1"));
    }

    @Test
    public void engineStatisTest() throws Exception {
        // check if the static field is correctly reset
        String result = engine.createGame(1).addBot(OnlyDice.class).addBot(SimpleHighestExploit.class).fire();
        String[] str = result.split("\n");
        assertEquals(3, str.length);
        String result1 = engine.createGame(2).addBot(OnlyDice.class).addBot(SimpleHighestExploit.class).fire();
        str = result1.split("\n");
        assertEquals(3, str.length);
        String result2 = engine.createGame(3).addBot(OnlyDice.class).addBot(SimpleHighestExploit.class).fire();
        str = result2.split("\n");
        assertEquals(3, str.length);
    }
}