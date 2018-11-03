package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import org.junit.Before;

/**
 * InventoryTest
 */
public class BoardTest {

    BoardExtends board;

    @Before
    public void setup() {
        board = new BoardExtends();
    }

    public void gameBoardTest() {
        board.BoardRegisterTest();
    }

    public void gameBoardForgeTest() {
        board.BoardForgeTest();
    }

}