package fr.unice.polytech.si3.ps5.teamb.diceforge.bot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;

public class BotTest {

    @Test
    public void botTest() {
        Game game = new Game();
        Bot bot = new Bot("pika");
        assertEquals(0, bot.getVictoryPoint());
        int pv_before = bot.getVictoryPoint();
        bot.play(game.getBoardView());
        assertTrue(pv_before <= bot.getVictoryPoint());
        pv_before = bot.getVictoryPoint();
        bot.play(game.getBoardView());
        assertTrue(pv_before <= bot.getVictoryPoint());
    }

    @Test
    public void equalityBotTest() {
        Game game = new Game();

        Bot bot1 = new Bot("b1");
        Bot bot2 = new Bot("b2");
        Bot bot3 = new Bot("b3");

        while (bot3.getVictoryPoint() == 0) {
            bot3.play(game.getBoardView());
        }

        assertTrue(bot1.equals(bot2));
        assertTrue(bot2.equals(bot1));
        assertFalse(bot3.equals(bot1));
        assertFalse(bot2.equals(bot3));
    }

}
