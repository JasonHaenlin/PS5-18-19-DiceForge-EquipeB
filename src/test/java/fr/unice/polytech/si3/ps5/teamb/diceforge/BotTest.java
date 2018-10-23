package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.maven.scm.command.export.ExportScmResultWithRevision;
import org.junit.Test;

public class BotTest {

    @Test
    public void botTest() {
        Bot bot = new Bot();
        Board board = new Board();
        board.createCard();
        assertEquals(0, bot.getVictoryPoint());
        int pv_before = bot.getVictoryPoint();
        bot.play(board);
        assertTrue(pv_before <= bot.getVictoryPoint());
        pv_before = bot.getVictoryPoint();
        bot.play(board);
        assertTrue(pv_before <= bot.getVictoryPoint());
    }

    @Test
    public void equalityBotTest() {
        Bot bot1 = new Bot("b1");
        Bot bot2 = new Bot("b2");
        Bot bot3 = new Bot("b3");
        Board board = new Board();
        board.createCard();

        while (bot3.getVictoryPoint() == 0) {
            bot3.play(board);
        }

        assertTrue(bot1.equals(bot2));
        assertTrue(bot2.equals(bot1));
        assertFalse(bot3.equals(bot1));
        assertFalse(bot2.equals(bot3));
    }

}
