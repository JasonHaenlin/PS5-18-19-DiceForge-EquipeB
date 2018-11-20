package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.SimpleHighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.ParseHelper;

/**
 * engineTest
 */
public class EngineTest {

    Engine engine = new Engine();

    @Test
    public void engineResultTest1() throws Exception {
        String result = engine.createGame(1).addBot(OnlyDice.class).addBot(SimpleHighestExploit.class).fire();
        Map<String, String> res = ParseHelper.parseResult("onlyDice", "SimpleHighestExploit", result);
        assertTrue(res.get("SimpleHighestExploit").contains("gagne 1"));
    }
}