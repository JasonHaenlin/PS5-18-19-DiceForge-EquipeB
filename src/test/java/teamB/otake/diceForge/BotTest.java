package teamB.otake.diceForge;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import teamB.otake.diceForge.Bot;

public class BotTest {

    @Test
    public void botTest(){
            Bot bot = new Bot();
            assertEquals(0, bot.getNombrePointDeGloire());
        }
    }

