package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice2;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class GameTest {

    String path = "src/test/resources/config/simplevpdice.json";
    Game game;

    @Before
    public void setup() throws Exception {
        game = new Game(new Config(path), 9);
    }

    @Test
    public void game2BotEqualsTest() throws Exception {
        String result = game.addBot(OnlyDice.class).addBot(OnlyDice2.class).fire();
        assertTrue(result.contains("18 points de Gloire") && result.contains("onlyDice2'")
                && result.contains("onlyDice'"));
    }
}
