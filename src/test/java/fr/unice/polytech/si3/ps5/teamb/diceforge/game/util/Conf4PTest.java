package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Cloud;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Hephaestos;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Raichu;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Totoro;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;

/**
 * ConfigTest
 */
public class Conf4PTest {

    Config conf;

    @Before
    public void setup() throws Exception {
        Engine engine = new Engine();
        engine.createGame(1).addBot(Hephaestos.class).addBot(Cloud.class).addBot(Raichu.class).addBot(Totoro.class);
        conf = new Config(engine.chooseConFile());

    }

    @Test
    public void extractExploitTest4P() {
        List<Card> cd = conf.extractExploit();
        assertEquals(4, cd.get(0).getSunStone());
        assertEquals(4, cd.get(1).getSunStone());
        assertEquals(4, cd.get(2).getSunStone());
        assertEquals(4, cd.get(3).getSunStone());
        assertEquals(0, cd.get(4).getSunStone());

    }

}