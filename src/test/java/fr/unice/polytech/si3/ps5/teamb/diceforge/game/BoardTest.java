package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import org.junit.Before;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * InventoryTest
 */
public class BoardTest {

    Config conf = new Config("src/test/resources/config/basic.json");

    BoardExtends board;

    @Before
    public void setup() {
        board = new BoardExtends(conf);
    }

    public void gameBoardTest() {
        board.BoardRegisterTest();
    }

    public void gameBoardForgeTest() {
        board.BoardForgeTest();
    }

}