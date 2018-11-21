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
        assertTrue(registrationToBoard("Lightning"));
        assertTrue(registrationToBoard("Cloud"));
        assertFalse(registrationToBoard("Cloud"));
        initialize();

        assertEquals(Collections.emptyList(), playableCards("Cloud"));
        assertEquals(Collections.emptyList(), playableSides("Cloud"));

        assertNotNull(rolldice("Cloud"));
        return true;
    }

    public boolean BoardForgeTest() {
        registrationToBoard("Cloud");
        initialize();
        assertEquals(6, playableSides("Cloud").size());
        return true;
    }

    public boolean BoardDiceTest() {
        registrationToBoard("Cloud");
        initialize();
        assertEquals(2, (int) rolldice("Cloud").get(Resources.GOLD));
        return true;
    }

    public boolean BoardCardTest() {
        registrationToBoard(bot.toString());
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
        registrationToBoard("Forgetest");
        initialize();
        temporaryAuthorization("Forgetest");
        return true;
    }

    public boolean BoardRepeatTest() {
        registrationToBoard("repeat");
        initialize();
        assertTrue(isPlayingAgainPossible("repeat"));
        removeResourcesToPlayAgain("repeat");
        assertFalse(isPlayingAgainPossible("repeat"));
        return true;
    }
}