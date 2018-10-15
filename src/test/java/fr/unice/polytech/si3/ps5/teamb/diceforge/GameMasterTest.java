package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameMasterTest {

    GameMaster gm;
    List<Bot> bots;

    @Test
    public void testWinner() {
        for (int i = 0; i < 1000; i++) {
            gm = new GameMaster();
            bots = new ArrayList<>();
            bots.add(new Bot("b1"));
            bots.add(new Bot("b2"));

            bots.get(0).play();
            bots.get(1).play();

            gm.etablishWinner(bots);

            assertTrue(isWinnerCorrect());
        }
    }

    private boolean isWinnerCorrect() {

        int winner = gm.getWinner().getVictoryPoint();
        int bot1 = bots.get(0).getVictoryPoint();
        int bot2 = bots.get(1).getVictoryPoint();

        if (winner == bot1 && winner != bot2) {
            return gm.getWinnerMsg().contains(bots.get(0).getName());
        }
        if (winner == bot2 && winner != bot1) {
            return gm.getWinnerMsg().contains(bots.get(1).getName());
        }
        return true;
    }
}
