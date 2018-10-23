package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.Bot;

public class GameMasterTest {

    GameMaster gm;
    List<Bot> bots;

    @Test
    public void testWinnerTwoPlayers() {
        for (int i = 0; i < 100; i++) {
            gm = new GameMaster();
            bots = new ArrayList<>();
            bots.add(new Bot("b1"));
            bots.add(new Bot("b2"));
            Board board = new Board();
            board.createCard();

            bots.get(0).play(board);
            bots.get(1).play(board);

            gm.etablishWinner(bots);

            assertTrue(this.isWinnerCorrectTwoPlayers());
        }
    }

    @Test
    public void testWinnerThreePlayers() {
        for (int i = 0; i < 100; i++) {
            gm = new GameMaster();
            bots = new ArrayList<>();
            bots.add(new Bot("b1"));
            bots.add(new Bot("b2"));
            bots.add(new Bot("b3"));
            Board board = new Board();
            board.createCard();

            bots.get(0).play(board);
            bots.get(1).play(board);
            bots.get(2).play(board);

            gm.etablishWinner(bots);

            assertTrue(this.isWinnerCorrectThreePlayers());
        }
    }

    private boolean isWinnerCorrectTwoPlayers() {

        ArrayList<Bot> listWinner = new ArrayList<>();
        listWinner = gm.getWinner();
        if (listWinner.size() == 2) {
            return gm.getWinnerMsg().contains(bots.get(0).getName())
                    && gm.getWinnerMsg().contains(bots.get(1).getName());
        } else {
            int winner = listWinner.get(0).getVictoryPoint();
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

    @Test
    public void testWinnerFourPlayers() {
        for (int i = 0; i < 100; i++) {
            gm = new GameMaster();
            bots = new ArrayList<>();
            bots.add(new Bot("b1"));
            bots.add(new Bot("b2"));
            bots.add(new Bot("b3"));
            bots.add(new Bot("b4"));
            Board board = new Board();
            board.createCard();

            bots.get(0).play(board);
            bots.get(1).play(board);
            bots.get(2).play(board);
            bots.get(3).play(board);

            gm.etablishWinner(bots);

            assertTrue(this.isWinnerCorrectFourPlayers());
        }
    }

    private boolean isWinnerCorrectThreePlayers() {
        ArrayList<Bot> listWinner = new ArrayList<>();
        listWinner = gm.getWinner();
        if (listWinner.size() == 3) {
            return gm.getWinnerMsg().contains(bots.get(0).getName())
                    && gm.getWinnerMsg().contains(bots.get(1).getName())
                    && gm.getWinnerMsg().contains(bots.get(2).getName());
        } else if (listWinner.size() == 2) {
            int winner = listWinner.get(0).getVictoryPoint();
            int bot1 = bots.get(0).getVictoryPoint();
            int bot2 = bots.get(1).getVictoryPoint();
            int bot3 = bots.get(2).getVictoryPoint();
            if (winner == bot1 && winner == bot2 && winner != bot3) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(1).getName());
            }
            if (winner == bot1 && winner == bot3 && winner != bot2) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
            if (winner == bot2 && winner == bot3 && winner != bot1) {
                return gm.getWinnerMsg().contains(bots.get(1).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
        } else {
            int winner = listWinner.get(0).getVictoryPoint();
            int bot1 = bots.get(0).getVictoryPoint();
            int bot2 = bots.get(1).getVictoryPoint();
            int bot3 = bots.get(2).getVictoryPoint();
            if (winner == bot1 && winner != bot2 && winner != bot3) {
                return gm.getWinnerMsg().contains(bots.get(0).getName());
            }
            if (winner == bot2 && winner != bot1 && winner != bot3) {
                return gm.getWinnerMsg().contains(bots.get(1).getName());
            }
            if (winner == bot3 && winner != bot2 && winner != bot1) {
                return gm.getWinnerMsg().contains(bots.get(2).getName());
            }
        }
        return true;
    }

    private boolean isWinnerCorrectFourPlayers() {
        ArrayList<Bot> listWinner = new ArrayList<>();
        listWinner = gm.getWinner();
        if (listWinner.size() == 4) {
            return gm.getWinnerMsg().contains(bots.get(0).getName())
                    && gm.getWinnerMsg().contains(bots.get(1).getName())
                    && gm.getWinnerMsg().contains(bots.get(2).getName())
                    && gm.getWinnerMsg().contains(bots.get(3).getName());
        } else if (listWinner.size() == 3) {
            int winner = listWinner.get(0).getVictoryPoint();
            int bot1 = bots.get(0).getVictoryPoint();
            int bot2 = bots.get(1).getVictoryPoint();
            int bot3 = bots.get(2).getVictoryPoint();
            int bot4 = bots.get(3).getVictoryPoint();
            if (winner == bot1 && winner == bot2 && winner == bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(1).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
            if (winner == bot1 && winner == bot2 && winner != bot3 && winner == bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(1).getName())
                        && gm.getWinnerMsg().contains(bots.get(3).getName());
            }
            if (winner == bot1 && winner != bot2 && winner == bot3 && winner == bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(3).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
            if (winner != bot1 && winner == bot2 && winner == bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(3).getName())
                        && gm.getWinnerMsg().contains(bots.get(1).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
        } else if (listWinner.size() == 2) {
            int winner = listWinner.get(0).getVictoryPoint();
            int bot1 = bots.get(0).getVictoryPoint();
            int bot2 = bots.get(1).getVictoryPoint();
            int bot3 = bots.get(2).getVictoryPoint();
            int bot4 = bots.get(3).getVictoryPoint();
            if (winner == bot1 && winner == bot2 && winner != bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(1).getName());
            }
            if (winner == bot1 && winner != bot2 && winner == bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
            if (winner == bot1 && winner != bot2 && winner != bot3 && winner == bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(3).getName());
            }
            if (winner != bot1 && winner == bot2 && winner == bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(1).getName())
                        && gm.getWinnerMsg().contains(bots.get(2).getName());
            }
            if (winner != bot1 && winner == bot2 && winner != bot3 && winner == bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName())
                        && gm.getWinnerMsg().contains(bots.get(3).getName());
            }
            if (winner != bot1 && winner != bot2 && winner == bot3 && winner == bot4) {
                return gm.getWinnerMsg().contains(bots.get(2).getName())
                        && gm.getWinnerMsg().contains(bots.get(3).getName());
            }
        } else {
            int winner = listWinner.get(0).getVictoryPoint();
            int bot1 = bots.get(0).getVictoryPoint();
            int bot2 = bots.get(1).getVictoryPoint();
            int bot3 = bots.get(2).getVictoryPoint();
            int bot4 = bots.get(3).getVictoryPoint();
            if (winner == bot1 && winner != bot2 && winner != bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(0).getName());
            }
            if (winner != bot1 && winner == bot2 && winner != bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(1).getName());
            }
            if (winner != bot1 && winner != bot2 && winner == bot3 && winner != bot4) {
                return gm.getWinnerMsg().contains(bots.get(2).getName());
            }
            if (winner != bot1 && winner != bot2 && winner != bot3 && winner == bot4) {
                return gm.getWinnerMsg().contains(bots.get(3).getName());
            }
        }
        return true;
    }
}
