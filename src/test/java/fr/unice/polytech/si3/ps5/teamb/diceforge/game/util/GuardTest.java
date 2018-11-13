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
        guard.add("bot1", 12345);
        guard.add("bot2", 12124);
    }

    @Test
    public void addPlayerAuthTest() {
        assertTrue(guard.add("bot3", 87654));
        assertFalse(guard.add("bot3", 87654));
    }

    @Test
    public void authTest() {
        assertFalse(guard.isAuthorizated("bot1"));
        guard.enableAuthorization("bot1");
        assertTrue(guard.isAuthorizated("bot1"));
        guard.removeAuthorization();
        assertFalse(guard.isAuthorizated("bot1"));
    }

    @Test
    public void repeatAuthTest() {
        guard.enableAuthorization("bot1");
        guard.removeAuthorization();
        assertTrue(guard.repeatAuth());
        assertTrue(guard.isAuthorizated("bot1"));
        guard.removeAuthorization();
        assertFalse(guard.repeatAuth());
        assertFalse(guard.isAuthorizated("bot1"));

    }
}