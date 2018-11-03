package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

public class TestActionForge {
    List<DiceSide> available; // Result of first test
    ActionForge forge;
    List<DiceSide> result;

    @Before
    public void setup() {
        available = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            DiceSide side = new DiceSide(i, Resources.GOLD);
            available.add(side);
        }
        forge = new ActionForge();
    }

    @Test
    public void testForgeAvailabe() {
        result = forge.forgeAvailable(4);
        for (int i = 0; i < 4; i++) {
            assertEquals(available.get(i).getValue(), result.get(i).getValue());
            assertEquals(available.get(i).getType(), result.get(i).getType());
        }
    }

}
