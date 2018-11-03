package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * ConfigTest
 */
public class ConfigTest {

    Config conf = new Config("src/test/resources/config/basic.json");

    @Test
    public void extractResourcesTest() {
        Map<Resources, Integer> rs = conf.extractInventory();
        assertEquals(3, rs.get(Resources.GOLD).intValue());
    }

    @Test
    public void extractDicesTest() {
        List<DiceSide> d1 = conf.extractDice(0);
        assertEquals(2, d1.get(0).getValue());
        assertEquals(Resources.VICTORY_POINT, d1.get(0).getType());
        List<DiceSide> d2 = conf.extractDice(1);
        assertEquals(1, d2.get(5).getValue());
        assertEquals(Resources.GOLD, d2.get(5).getType());
    }

    @Test
    public void extractExploitTest() {
        List<Card> cd = conf.extractExploit();
        assertEquals(3, cd.get(0).getSunStone());
        assertEquals(5, cd.get(2).getVictoryPoint());
    }

    @Test
    public void extractForgeTest() {
        Map<Integer, List<DiceSide>> fg = conf.extractForge();
        assertEquals(1, fg.get(3).get(0).getValue());
        assertEquals(Resources.VICTORY_POINT, fg.get(3).get(0).getType());
        assertEquals(3, fg.get(5).get(2).getValue());
        assertEquals(Resources.VICTORY_POINT, fg.get(3).get(2).getType());
    }
}