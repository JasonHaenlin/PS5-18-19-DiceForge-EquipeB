package teamB.otake.diceForge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class GameTest {

    @Test
    public void testLog() {
        Game game = new Game();

        String result = game.setUp(1).addBot("Rondoudou").addBot("Pichu").fire();

        assertTrue(!result.isEmpty());
    }
}
