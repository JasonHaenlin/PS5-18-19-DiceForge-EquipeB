package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * BoardExtends
 */
public class BoardExtends extends Board {

    public BoardExtends(Config conf) {
        super(conf);
    }

    public boolean BoardRegisterTest() {
        assertTrue(registrationToBoard("Lightning", 1));
        assertTrue(registrationToBoard("Cloud", 1));
        assertFalse(registrationToBoard("Cloud", 1));
        initialize();

        assertEquals(Collections.emptyList(), playableCards("Cloud"));
        assertEquals(Collections.emptyList(), playableSides("Cloud"));

        assertNotNull(rolldice("Cloud"));
        return true;
    }

    public boolean BoardForgeTest() {
        registrationToBoard("Cloud", 1);
        initialize();
        assertEquals(6, playableSides("Cloud").size());
        return true;
    }

    public boolean BoardDiceTest() {
        registrationToBoard("Cloud", 1);
        initialize();
        assertEquals(2, (int) rolldice("Cloud").get(Resources.GOLD));
        return true;
    }

    public boolean BoardCardTest() {
        registrationToBoard("Cloud", 1);
        initialize();
        assertEquals(4, playableCards("Cloud").size());
        return true;
    }

    public boolean TestBoardforge() {
        registrationToBoard("Forgetest", 1);
        initialize();
        return true;
    }
}