package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Temple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class forgeTest {

    Config conf;

    Temple forge;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        forge = new Temple(conf.getForgeConfig());
    }

    @Test
    public void forgeAvailabeTest() {
        assertEquals(3, forge.obtainReplaceableSides(3).size());
        assertEquals(9, forge.obtainReplaceableSides(5).size());
    }

    @Test
    public void forgeRemoveSideTest() {
        List<DiceSide> sides = forge.obtainReplaceableSides(3);
        System.out.println(sides.toString());
        forge.removeSide(sides.get(0));
        assertEquals(0, forge.obtainReplaceableSides(3).size());
        assertEquals(3, forge.obtainReplaceableSides(4).size());
        assertFalse(forge.removeSide(sides.get(1)));
        assertFalse(forge.removeSide(sides.get(2)));
        forge.resetTurn();
        assertTrue(forge.removeSide(sides.get(1)));
        forge.resetTurn();
        assertTrue(forge.removeSide(sides.get(2)));
        assertEquals(0, forge.obtainReplaceableSides(3).size());
        forge.resetTurn();
        assertFalse(forge.removeSide(sides.get(0)));
    }

}
