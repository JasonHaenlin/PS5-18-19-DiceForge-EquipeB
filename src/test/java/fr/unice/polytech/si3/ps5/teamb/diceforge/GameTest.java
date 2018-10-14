package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class GameTest {

    @Test
    public void game2BotTest() {
        Game game = new Game();

        String result = game.setUp(1).addBot("Rondoudou").addBot("Pichu").fire();

        assertTrue(!result.isEmpty());
    }

    @Test
    public void game3BotTest() {
        Game game = new Game();

        String result = game.setUp(1).addBot("Rondoudou").addBot("Pichu").addBot("Ronflex").fire();

        assertTrue(!result.isEmpty());
    }
}
