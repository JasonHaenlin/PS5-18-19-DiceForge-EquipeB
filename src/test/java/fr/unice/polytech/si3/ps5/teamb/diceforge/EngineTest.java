package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.Assert.assertTrue;

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
        engine.setConfFile("src/test/resources/config/moonDiceAndSunDice.json");
        String result = engine.createGame(100).addBot(OnlyDice.class).addBot(SimpleHighestExploit.class).fire();
        Map<String, String> res = ParseHelper.parseResult("onlyDice", "SimpleHighestExploit", result);
        assertTrue(res.get("SimpleHighestExploit").contains("gagne 100"));
    }
}