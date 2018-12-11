package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;

/**
 * ConfigTest
 */
public class ConfigTest {

    Config conf;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
    }

    @Test
    public void extractResourcesTest() {
        Map<Resources, Integer> rs = conf.extractInventory();
        assertEquals(3, rs.get(Resources.GOLD).intValue());
    }

    @Test
    public void extractDicesTest() {
        List<DiceSide> d1 = conf.extractDice(0);
        assertEquals(2, d1.get(0).coefficient());
        assertTrue(d1.get(0).contains(Resources.VICTORY_POINT));

        List<DiceSide> d2 = conf.extractDice(1);
        assertEquals(1, d2.get(5).coefficient());
        assertTrue(d2.get(5).contains(Resources.GOLD));
    }

    @Test
    public void extractExploitTest() {
        List<Card> cd = conf.extractExploit();
        assertEquals(0, cd.get(0).getSunStone());
        assertEquals(12, cd.get(2).getVictoryPoints());
    }

    @Test
    public void extractForgeTest() {
        Map<Integer, List<DiceSide>> fg = conf.extractForge();
        assertEquals(1, fg.get(3).get(0).coefficient());
        assertTrue(fg.get(3).get(0).contains(Resources.VICTORY_POINT));
        assertEquals(3, fg.get(5).get(2).coefficient());
        assertTrue(fg.get(3).get(2).contains(Resources.VICTORY_POINT));
    }

    @Test
    public void failExtractTest() {
        try {
            new Config("path/to/file/json");
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

}