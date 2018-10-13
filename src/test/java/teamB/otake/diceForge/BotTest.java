package teamB.otake.diceForge;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
