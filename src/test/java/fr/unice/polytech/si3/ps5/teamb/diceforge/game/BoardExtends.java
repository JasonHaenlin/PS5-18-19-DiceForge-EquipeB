package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * BoardExtends
 */
public class BoardExtends extends Board {
    Player bot;

    public BoardExtends(Config conf) {
        super(conf);
    }

    public BoardExtends(Config conf, Player bot) {
        super(conf);
        this.bot = bot;
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
        registrationToBoard("Carl", 2);
        registrationToBoard("Philipe", 3);
        initialize();
        assertEquals(6, playableSides("Cloud").size());
        assertEquals(3, playableSides("Carl").size());
        assertEquals(0, playableSides("Philipe").size());

        return true;
    }

    public boolean BoardDiceTest() {
        registrationToBoard("Cloud", 1);
        initialize();
        assertEquals(2, (int) rolldice("Cloud").get(Resources.GOLD));
        return true;
    }

    public boolean BoardCardTest() {
        registrationToBoard(bot.toString(), 1);
        initialize();
        List<Card> card = playableCards(bot.toString());
        temporaryAuthorization(bot.toString());
        assertEquals(4, card.size());
        exploit(card.get(0), bot.toString());
        playLastCard(bot);
        assertTrue(getVictoryPoints(bot.toString()) != 0);
        return true;
    }

    public boolean TestBoardforge() {
        registrationToBoard("Forgetest", 1);
        initialize();
        temporaryAuthorization("Forgetest");
        return true;
    }

    public boolean BoardRepeatTest() {
        registrationToBoard("repeat", 1);
        initialize();
        assertTrue(isPlayingAgainPossible("repeat"));
        removeResourcesToPlayAgain("repeat");
        assertFalse(isPlayingAgainPossible("repeat"));
        return true;
    }
}