package fr.unice.polytech.si3.ps5.teamb.diceforge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DiceTest {

    Dice dice1;
    Dice dice2;

    @Before
    public void setUp() {
        dice1 = new Dice();
    }

    @Test
    public void diceTestValue() {
        dice1.random();
        int r1 = dice1.getRandomValue();
        assertTrue(r1 <= 2 && r1 >= 0);
        dice1.random();
        int r2 = dice1.getRandomValue();
        assertTrue(r2 <= 2 && r2 >= 0);
    }

    @Test
    public void diceTestResources(){
        dice1.random();
        Resources r1 = dice1.getRandomResources();
        assertTrue(r1 == Resources.PG || r1 ==Resources.G ||
                r1 == Resources.MS || r1 == Resources.SS);
        dice1.random();
        Resources r2 = dice1.getRandomResources();
        assertTrue(r2 == Resources.PG || r2 ==Resources.G ||
                r2 == Resources.MS || r2 == Resources.SS);
    }

    @Test
    public void diceResTest() {
        List<DiceSide> diceSide = new ArrayList<>();
        diceSide.add(new DiceSide(1, Resources.PG));
        diceSide.add(new DiceSide(2, Resources.PG));
        diceSide.add(new DiceSide(3, Resources.PG));
        diceSide.add(new DiceSide(4, Resources.PG));
        diceSide.add(new DiceSide(5, Resources.PG));
        diceSide.add(new DiceSide(6, Resources.PG));
        dice2 = new Dice(diceSide);
        dice2.random();
        assertEquals(Resources.PG.toString(), dice2.diceFaceType());
    }
}