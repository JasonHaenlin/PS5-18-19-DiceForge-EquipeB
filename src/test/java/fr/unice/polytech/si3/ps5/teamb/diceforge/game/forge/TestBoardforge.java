package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.BoardExtends;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * TestBoardforge
 */
public class TestBoardforge {

    private Config conf;
    private BoardExtends board;
    private DiceSide remove = new DiceSide(1, Resources.MOON_STONE);
    private DiceSide add = new DiceSide(1, Resources.VICTORY_POINT, 3);

    @Before
    public void setup() throws Exception {
        String confFile = "src/test/resources/config/basic.json";
        conf = new Config(confFile);
        board = new BoardExtends(conf);

    }

    @Test
    public void boardForgeTest() {
        board.boardforge2Test();
        assertTrue(board.forge("Forgetest", 0, remove, add));
        assertTrue(checkremove());

    }

    public boolean checkremove() {
        List<DiceSide> dicesides = board.getDiceSide("Forgetest", 0);
        int intadd[] = { 0 };
        int intremove[] = { 1 };
        for (DiceSide diceside : dicesides) {

            if (diceside.equals(add)) {
                intadd[0] = 1;
            }
            if (diceside.equals(remove)) {
                intremove[0] = 0;
            }
        }
        System.out.println("intadd : " + intadd[0] + " intremove :" + intremove[0]);
        if (intadd[0] == 1 && intremove[0] == 1) {
            return true;
        }
        return false;
    }
}
