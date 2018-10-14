package teamb.otake.diceforge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class BotTest {

    @Test
    public void botTest() {
        Bot bot = new Bot();
        assertEquals(0, bot.getVictoryPoint());
        int pv_before = bot.getVictoryPoint();
        bot.play();
        assertTrue(pv_before <= bot.getVictoryPoint());
        pv_before = bot.getVictoryPoint();
        bot.play();
        assertTrue(pv_before <= bot.getVictoryPoint());
    }

    @Test
    public void equalityBotTest() {
        Bot bot1 = new Bot("b1");
        Bot bot2 = new Bot("b2");
        Bot bot3 = new Bot("b3");

        while (bot3.getVictoryPoint() == 0) {
            bot3.play();
        }

        assertTrue(bot1.equals(bot2));
        assertTrue(bot2.equals(bot1));
        assertFalse(bot3.equals(bot1));
        assertFalse(bot2.equals(bot3));
    }
}
