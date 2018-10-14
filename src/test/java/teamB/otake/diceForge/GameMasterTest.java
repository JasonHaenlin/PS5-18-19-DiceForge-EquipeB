package teamB.otake.diceForge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class GameMasterTest {

    @Test
    public void testWinner() {
        for (int i = 0; i < 100; i++) {
            GameMaster gameMaster = new GameMaster();

            Bot bot1 = new Bot();
            Bot bot2 = new Bot();

            bot1.play();
            bot2.play();

            gameMaster.etablishWinner(bot1, bot2);

            int scoreBot1 = gameMaster.getScoreBot1();
            int scoreBot2 = gameMaster.getScoreBot2();

            if (scoreBot1 < scoreBot2) {
                assertEquals("Le bot 2 a gagné avec un score de : " + scoreBot2, gameMaster.getWinnerMsg());
            } else if (scoreBot2 < scoreBot1) {
                assertEquals("Le bot 1 a gagné avec un score de : " + scoreBot1, gameMaster.getWinnerMsg());
            } else {
                assertEquals("Les deux bots sont à égalité avec un score de : " + scoreBot1, gameMaster.getWinnerMsg());
            }
        }
    }
}

