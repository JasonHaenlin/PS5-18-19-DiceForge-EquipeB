package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class TestActionForge {

    Config conf;

    ActionForge forge;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        forge = new ActionForge(conf.getForgeConfig());
    }

    @Test
    public void forgeAvailabeTest() {
        assertEquals(3, forge.availableSides(3).size());
        assertEquals(9, forge.availableSides(5).size());
    }

    @Test
    public void forgeRemoveSideTest() {
        List<DiceSide> sides = forge.availableSides(3);
        forge.removeSide(sides.get(0));
        assertEquals(2, forge.availableSides(3).size());
        assertEquals(5, forge.availableSides(4).size());
        assertTrue(forge.removeSide(sides.get(1)));
        assertTrue(forge.removeSide(sides.get(2)));
        assertEquals(0, forge.availableSides(3).size());
        assertFalse(forge.removeSide(sides.get(0)));
    }

}
