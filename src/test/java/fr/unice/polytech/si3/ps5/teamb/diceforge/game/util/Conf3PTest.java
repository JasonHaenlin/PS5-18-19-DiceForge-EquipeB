package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.forgeVP;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;

/**
 * ConfigTest
 */
public class Conf3PTest {

    Config conf;

    @Before
    public void setup() throws Exception {
        Engine engine = new Engine();
        engine.createGame(1).addBot(forgeVP.class).addBot(forgeVP.class).addBot(forgeVP.class);
        conf = new Config(engine.chooseConFile());

    }

    @Test
    public void extractExploitTest4P() {
        List<Card> cd = conf.extractExploit();
        assertEquals(4, cd.get(0).getSunStone());
        assertEquals(4, cd.get(1).getSunStone());
        assertEquals(4, cd.get(2).getSunStone());
        assertEquals(0, cd.get(3).getSunStone());

    }

}