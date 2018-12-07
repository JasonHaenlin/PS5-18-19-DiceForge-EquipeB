package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * TempleTest
 */
public class TempleTest {

    static final String file = ("src/test/resources/config/basicforgepool.json");
    Config conf;

    Temple temple;
    List<Pool> pool;
    Map<Integer, List<DiceSide>> temp;
    DiceSide side1 = new SideSimple(Resources.GOLD, 1, 3);
    DiceSide side2 = new SideSimple(Resources.GOLD, 1, 4);
    DiceSide side3 = new SideSimple(Resources.GOLD, 1, 5);

    @Before
    public void setup() {

        temp = new HashMap<>();
        temp.put(3, new ArrayList<>(Arrays.asList(side1, side1, side1)));
        temp.put(4, new ArrayList<>(Arrays.asList(side2, side2, side2)));
        temp.put(5, new ArrayList<>(Arrays.asList(side3, side3, side3)));
    }

    @Test
    public void AvailableSideTest() {
        temple = new Temple(temp);
        assertEquals(3, temple.obtainReplaceableSides(3).size());
        assertEquals(6, temple.obtainReplaceableSides(4).size());
        assertEquals(9, temple.obtainReplaceableSides(5).size());
    }

    @Test
    public void removeSideTest() {
        temple = new Temple(temp);
        assertTrue(temple.removeSide(side1));
        assertEquals(0, temple.obtainReplaceableSides(3).size());
        assertTrue(temple.removeSide(side2));
        assertEquals(3, temple.obtainReplaceableSides(5).size());
        assertTrue(temple.removeSide(side3));
        assertEquals(0, temple.obtainReplaceableSides(4).size());
        temple.resetTurn();
        assertEquals(6, temple.obtainReplaceableSides(5).size());
    }

    @Test
    public void checkPooltest() throws Exception {
        conf = new Config(file);
        temple = new Temple(conf.getForgeConfig());
        assertEquals(0, temple.obtainReplaceableSides(1).size());
        assertEquals(8, temple.obtainReplaceableSides(2).size());
        assertEquals(16, temple.obtainReplaceableSides(3).size());
        assertEquals(16, temple.obtainReplaceableSides(6).size());
        assertEquals(24, temple.obtainReplaceableSides(8).size());
        assertEquals(25, temple.obtainReplaceableSides(12).size());
    }
}