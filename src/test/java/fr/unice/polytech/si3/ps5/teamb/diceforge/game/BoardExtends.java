package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;
import fr.unice.polytech.si3.ps5.teamb.diceforge.helper.PlayerTest;

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
        assertTrue(registrationToBoard(PlayerTest.first.toString()));
        assertFalse(registrationToBoard(PlayerTest.first.toString()));
        initialize();

        assertEquals(Collections.emptyList(), playableCards(PlayerTest.first.toString()));
        assertEquals(Collections.emptyList(), playableSides(PlayerTest.first.toString()));

        assertNotNull(rolldice(PlayerTest.first));
        return true;
    }

    public boolean BoardForgeTest() {
        registrationToBoard("Cloud");
        registrationToBoard("Carl");
        registrationToBoard("Philipe");
        initialize();
        assertEquals(6, playableSides("Cloud").size());
        assertEquals(3, playableSides("Carl").size());
        assertEquals(0, playableSides("Philipe").size());
        return true;
    }

    public boolean BoardCheatForgeTest() {
        registrationToBoard("noCheat");
        initialize();
        temporaryAuthorization("noCheat");
        return true;
    }

    public boolean BoardDiceTest() {
        registrationToBoard(PlayerTest.first.toString());
        initialize();
        assertEquals(2, (int) rolldice(PlayerTest.first).get(Resources.GOLD));
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

    public boolean BoardCardTest2() {
        registrationToBoard(bot.toString());
        initialize();
        List<Card> card = playableCards(bot.toString(), Resources.SUN_STONE, 2);
        assertEquals(2, card.size());
        card = playableCards(bot.toString(), Resources.SUN_STONE, -2);
        assertEquals(4, card.size());
        return true;
    }

    public boolean boardforge2Test() {
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