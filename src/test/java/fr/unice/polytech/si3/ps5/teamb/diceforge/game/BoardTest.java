package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * InventoryTest
 */
public class BoardTest {

    static final String basiccardConf = ("src/test/resources/config/basiccard.json");
    static final String basic = ("src/test/resources/config/basic.json");
    static final String basicdiceConf = ("src/test/resources/config/basicdice.json");
    static final String basicsideConf = ("src/test/resources/config/basicside.json");
    static final String noresConf = ("src/test/resources/config/nores.json");

    BoardExtends board;

    @Test
    public void gameBoardTest() throws Exception {
        board = new BoardExtends(new Config(noresConf));
        assertTrue(board.BoardRegisterTest());
    }

    @Test
    public void gameBoardForgeTest() throws Exception {
        board = new BoardExtends(new Config(basicsideConf));
        assertTrue(board.BoardForgeTest());
    }

    @Test
    public void gameBoardDiceTest() throws Exception {
        board = new BoardExtends(new Config(basicdiceConf));
        assertTrue(board.BoardDiceTest());
    }

    @Test
    public void gameBoardCardTest() throws Exception {
        board = new BoardExtends(new Config(basiccardConf));
        assertTrue(board.BoardCardTest());
    }

    @Test
    public void repeatMoveAgainTest() throws Exception {
        board = new BoardExtends(new Config(basic));
        assertTrue(board.BoardRepeatTest());
    }

}