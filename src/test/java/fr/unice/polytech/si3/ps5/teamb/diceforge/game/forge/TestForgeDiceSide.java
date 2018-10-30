package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestForgeDiceSide {

    Map<Integer,Integer>sides;
    List<Integer>available;
    ForgeDiceSide forge;

    @Before
    public void setup(){
        sides = new HashMap<>();
        available = new ArrayList<>();
        for(int i =1; i<7;i++){
            sides.put(i,i);
            if(i<=4){available.add(i);}
        }
        forge = new ForgeDiceSide(sides);
    }

    @Test
    public void testForgeAvailabe(){
        assertEquals(available, forge.forgeAvailable(4));
    }
}
