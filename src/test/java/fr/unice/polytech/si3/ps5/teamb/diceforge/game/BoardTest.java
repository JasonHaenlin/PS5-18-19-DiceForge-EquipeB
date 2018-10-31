package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import org.junit.Before;
import org.junit.Test;

/**
 * InventoryTest
 */
public class BoardTest {

    BoardExtends board;

    @Before
    public void setup() {
        board = new BoardExtends();
    }

    @Test
    public void gameBoardTest() {
        board.BoardTest();
    }
}