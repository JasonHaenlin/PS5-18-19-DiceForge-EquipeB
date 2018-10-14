package teamb.otake.diceforge;

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
    public void diceTest() {
        int r1 = dice1.random();
        assertTrue(r1 <= 6 && r1 >= 0);
        int r2 = dice1.random();
        assertTrue(r2 <= 6 && r2 >= 0);
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