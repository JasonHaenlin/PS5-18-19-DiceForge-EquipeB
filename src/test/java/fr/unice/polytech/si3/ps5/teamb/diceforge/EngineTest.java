package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Totoro;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.ParseHelper;

/**
 * engineTest
 */
public class EngineTest {

    Engine engine = new Engine();


    @Test
    public void engineResultTest1() throws Exception { //Totoro is a SimpleHighestExploit strategy bot
        String result = engine.createGame(1).addBot(OnlyDice.class).addBot(Totoro.class).fire();
        Map<String, String> res = ParseHelper.parseResult("onlyDice", "Totoro", result);
        System.out.println(ParseHelper.parseResult("onlyDice", "Totoro", result));
        assertTrue(res.get("Totoro").contains("gagne 1"));
    }
}