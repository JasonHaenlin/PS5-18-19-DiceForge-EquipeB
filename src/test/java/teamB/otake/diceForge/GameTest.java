package teamB.otake.diceForge;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import teamB.otake.diceForge.Game;

public class GameTest {

    @Test
    public void testLog(){
        Game game = new Game();
        assertNotEquals("", game.getLog());

    }
}
