package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.SimpleCard;

/**
 * InventoryTest
 */
public class InventoryTest {

    Inventory inv;

    @Before
    public void setup() {
        inv = new Inventory();
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
        Card card = new SimpleCard(5, 5, 10);
        assertFalse(inv.addCardToBag(card));
        inv.addResourceToBag(5, Resources.MOON_STONE);
        inv.addResourceToBag(7, Resources.SUN_STONE);
        assertTrue(inv.addCardToBag(card));
        assertEquals(0, inv.getResource(Resources.MOON_STONE));
        assertEquals(2, inv.getResource(Resources.SUN_STONE));
        assertEquals(10, inv.getLastVIctoryPoint());
        assertEquals(0, inv.getLastVIctoryPoint());
    }
}