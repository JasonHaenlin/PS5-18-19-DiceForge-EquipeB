package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * BoardExtends
 */
public class BoardExtends extends Board {

    public BoardExtends() {
        super();
    }

    public void BoardRegisterTest() {
        assertTrue(registrationToBoard("Lightning", 1));
        assertTrue(registrationToBoard("Cloud", 1));
        assertFalse(registrationToBoard("Cloud", 1));
        initialize();

        assertEquals(Collections.emptyList(), getEligibleCards("Cloud"));
        assertNotNull(rolldice("Cloud"));
    }

    public void BoardForgeTest() {
        assertEquals(Collections.emptyList(), getEligibleSides(0));
    }
}