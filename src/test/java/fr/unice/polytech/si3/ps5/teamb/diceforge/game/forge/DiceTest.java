package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

public class DiceTest {

    Map<Resources, Integer> treasury;
    Dice dice1;
    Dice dice2;

    @Before
    public void setUp() {
        treasury = new HashMap<>();
        for (Resources rsc : Resources.values()) {
            treasury.put(rsc, 0);
        }
        dice1 = new Dice();
    }

    @Test
    public void diceTestValue() {
        dice1.random(treasury);
        int r1 = dice1.getRandomValue();
        assertTrue(r1 <= 2 && r1 >= 0);
        dice1.random(treasury);
        int r2 = dice1.getRandomValue();
        assertTrue(r2 <= 2 && r2 >= 0);
    }

    @Test
    public void diceTestResources() {
        dice1.random(treasury);
        Resources r1 = dice1.getRandomResources();
        assertTrue(r1 == Resources.VICTORY_POINT || r1 == Resources.GOLD || r1 == Resources.MOON_STONE
                || r1 == Resources.SUN_STUNE);
        dice1.random(treasury);
        Resources r2 = dice1.getRandomResources();
        assertTrue(r2 == Resources.VICTORY_POINT || r2 == Resources.GOLD || r2 == Resources.MOON_STONE
                || r2 == Resources.SUN_STUNE);
    }

    @Test
    public void diceResTest() {
        List<DiceSide> diceSide = new ArrayList<>();
        diceSide.add(new DiceSide(1, Resources.VICTORY_POINT));
        diceSide.add(new DiceSide(2, Resources.VICTORY_POINT));
        diceSide.add(new DiceSide(3, Resources.VICTORY_POINT));
        diceSide.add(new DiceSide(4, Resources.VICTORY_POINT));
        diceSide.add(new DiceSide(5, Resources.VICTORY_POINT));
        diceSide.add(new DiceSide(6, Resources.VICTORY_POINT));
        dice2 = new Dice(diceSide);
        dice2.random(treasury);
        assertEquals(Resources.VICTORY_POINT, dice2.getRandomResources());
    }
}