package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Hydra;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * InventoryTest
 */
public class InventoryTest {

    Config conf;

    Inventory inv;
    Inventory invPerso;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        inv = new Inventory();
        invPerso = new Inventory(conf.getInvConfig(), conf.getDice1Config(), conf.getDice2Config());
    }

    @Test
    public void StartWithEmptyBagTest() {
        assertEquals(0, inv.getResource(Resources.GOLD));
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
        assertEquals(0, inv.getResource(Resources.MOON_STONE));
        assertEquals(0, inv.getResource(Resources.SUN_STONE));
    }

    @Test
    public void AddResourcesToBagTest() {
        inv.addResourceToBag(5, Resources.GOLD);
        assertEquals(5, inv.getResource(Resources.GOLD));
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
        assertEquals(0, inv.getResource(Resources.MOON_STONE));
        assertEquals(0, inv.getResource(Resources.SUN_STONE));
        inv.addResourceToBag(10, Resources.MOON_STONE);
        assertEquals(5, inv.getResource(Resources.GOLD));
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
        assertEquals(10, inv.getResource(Resources.MOON_STONE));
        assertEquals(0, inv.getResource(Resources.SUN_STONE));
    }

    @Test
    public void RemoveResourcesFromBagTest() {
        inv.addResourceToBag(5, Resources.GOLD);
        inv.addResourceToBag(29, Resources.VICTORY_POINT);
        inv.removeResourceFromBag(9, Resources.VICTORY_POINT);
        assertEquals(20, inv.getResource(Resources.VICTORY_POINT));
        inv.removeResourceFromBag(20, Resources.VICTORY_POINT);
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
        inv.removeResourceFromBag(5, Resources.VICTORY_POINT);
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
    }

    @Test
    public void addCardToBagTest() {
        Card card = new Hydra(5, 5, 10);
        assertFalse(inv.addCardToBag(card));
        inv.addResourceToBag(5, Resources.MOON_STONE);
        inv.addResourceToBag(7, Resources.SUN_STONE);
        assertTrue(inv.addCardToBag(card));
        assertEquals(0, inv.getResource(Resources.MOON_STONE));
        assertEquals(2, inv.getResource(Resources.SUN_STONE));
        assertEquals(10, inv.pollLastVictoryPoint());
        assertEquals(0, inv.pollLastVictoryPoint());
    }

    @Test
    public void replaceDiceSideTest() {
        Dice dice1 = invPerso.getDice(0);
        Dice dice2 = invPerso.getDice(1);
        assertFalse(dice1.equals(dice2));

        DiceSide side1 = new DiceSide(2, Resources.VICTORY_POINT);
        DiceSide side2 = new DiceSide(1, Resources.MOON_STONE);

        assertTrue(invPerso.replaceDiceSide(1, dice2.getDiceSides().get(0), side1));
        assertTrue(invPerso.replaceDiceSide(1, dice2.getDiceSides().get(1), side2));

        assertTrue(dice1.equals(dice2));

    }
}