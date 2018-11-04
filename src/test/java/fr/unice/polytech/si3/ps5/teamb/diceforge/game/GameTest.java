package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.SimpleHighestExploit;

public class GameTest {
    Engine engine;

    @Before
    public void setup() {
        this.engine = new Engine();
    }

    // TO DO
    @Test
    public void game2BotTest() throws Exception {
        // @formatter:off
        engine.createGame(10)
                .addBot(OnlyDice.class)
                .addBot(SimpleHighestExploit.class)
                .fire();
        //@formatter:ons
    }
}
