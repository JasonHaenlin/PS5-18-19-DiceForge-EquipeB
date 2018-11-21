package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * GuardTest
 */
public class GuardTest {

    Guard guard;

    @Before
    public void setup() {
        guard = new Guard(2);
    }

    @Test
    public void authTest() {
        assertFalse(guard.isAuthorizated("bot1", 1));
        guard.enableAuthorization("bot1");
        assertTrue(guard.isAuthorizated("bot1", 1));
        guard.revokeAuthorization();
        assertFalse(guard.isAuthorizated("bot1", 1));
    }

    @Test
    public void authPartialTest() {
        assertFalse(guard.isAuthorizated("bot1", 1));
        assertFalse(guard.isAuthorizated("bot1", 2));
        guard.enableAuthorization("bot1");
        assertTrue(guard.isAuthorizated("bot1", 1));
        assertTrue(guard.isAuthorizated("bot1", 2));

        guard.revokeAuthorizationPartially("bot1", 1);
        assertFalse(guard.isAuthorizated("bot1", 1));
        assertTrue(guard.isAuthorizated("bot1", 2));

        guard.revokeAuthorizationPartially("bot1", 2);
        assertFalse(guard.isAuthorizated("bot1", 1));
        assertFalse(guard.isAuthorizated("bot1", 2));

        assertTrue(guard.peekLastPlayer().equals("bot1"));
    }

}