package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.BlacksmithHammer;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Hydra;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;
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
        inv.basicSet();
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
        assertEquals(6, inv.getResource(Resources.MOON_STONE));
        assertEquals(0, inv.getResource(Resources.SUN_STONE));
        inv.addResourceToBag(10, Resources.GOLD);
        assertEquals(12, inv.getResource(Resources.GOLD));
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
        assertEquals(6, inv.getResource(Resources.MOON_STONE));
        assertEquals(0, inv.getResource(Resources.SUN_STONE));
        inv.addResourceToBag(10, Resources.SUN_STONE);
        assertEquals(12, inv.getResource(Resources.GOLD));
        assertEquals(0, inv.getResource(Resources.VICTORY_POINT));
        assertEquals(6, inv.getResource(Resources.MOON_STONE));
        assertEquals(6, inv.getResource(Resources.SUN_STONE));
        inv.addResourceToBag(24, Resources.VICTORY_POINT);
        assertEquals(12, inv.getResource(Resources.GOLD));
        assertEquals(24, inv.getResource(Resources.VICTORY_POINT));
        assertEquals(6, inv.getResource(Resources.MOON_STONE));
        assertEquals(6, inv.getResource(Resources.SUN_STONE));
        inv.expand(4, 3, 3);
        inv.addResourceToBag(3, Resources.GOLD);
        assertEquals(15, inv.getResource(Resources.GOLD));
        inv.addResourceToBag(3, Resources.GOLD);
        assertEquals(16, inv.getResource(Resources.GOLD));

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
        assertEquals(1, inv.getResource(Resources.SUN_STONE));
        assertEquals(20, inv.pollLastVictoryPoint());
        assertEquals(0, inv.pollLastVictoryPoint());
    }

    @Test
    public void replaceDiceSideTest() {
        Dice dice1 = invPerso.getDice(0);
        Dice dice2 = invPerso.getDice(1);
        assertFalse(dice1.equals(dice2));

        DiceSide side1 = new SideSimple(Resources.VICTORY_POINT, 2, 0);
        DiceSide side2 = new SideSimple(Resources.MOON_STONE, 1, 0);

        assertTrue(invPerso.replaceDiceSide(1, dice2.getDiceSides().get(0), side1));
        assertTrue(invPerso.replaceDiceSide(1, dice2.getDiceSides().get(1), side2));

        assertTrue(dice1.equals(dice2));

    }

    @Test
    public void HammerCardEffectTest() {
        BlacksmithHammer bl = new BlacksmithHammer(1, 0, 0);
        bl.setCardOwner(HelperPlayer.first);
        inv.addHammerEffect(bl);
        inv.addResourceToBag(5, Resources.GOLD);
        assertEquals(0, inv.getResource(Resources.GOLD));
        assertFalse(bl.isHammerDone());

        bl.setCardOwner(HelperPlayer.fifth);
        inv.addResourceToBag(5, Resources.GOLD);
        assertEquals(5, inv.getResource(Resources.GOLD));

        bl.setCardOwner(HelperPlayer.first);
        inv.addResourceToBag(30, Resources.GOLD);
        assertEquals(10, inv.getResource(Resources.GOLD));
        assertTrue(bl.isHammerDone());

        bl.setCardOwner(HelperPlayer.fifth);
        inv.addResourceToBag(10, Resources.GOLD);
        assertEquals(12, inv.getResource(Resources.GOLD));
    }
}