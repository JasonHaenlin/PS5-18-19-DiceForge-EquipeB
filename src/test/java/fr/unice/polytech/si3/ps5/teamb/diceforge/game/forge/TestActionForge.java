package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Inventory;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestActionForge {

    Map<Integer,DiceSide>sides;
    List<DiceSide> diceSides; //To create dice with only gold
    List<DiceSide>available; //Result of first test
    List<DiceSide> result; //Result of second test
    ActionForge forge;
    Inventory inventory;
    Dice dice;

    @Before
    public void setup(){
        sides = new HashMap<>();
        result = new ArrayList<>();
        diceSides = new ArrayList<>();
        available = new ArrayList<>();
        inventory = new Inventory();
        for(int i =1; i<7;i++){
            DiceSide side = new DiceSide(i, Resources.GOLD);
            sides.put(i,side);
            diceSides.add(side);
            result.add(new DiceSide(1,Resources.GOLD));
            if(i<=4){available.add(side);}
        }
        forge = new ActionForge(sides, inventory);
        dice = new Dice(diceSides); //Create dice with only gold
    }

    @Test
    public void testForgeAvailabe(){
        assertEquals(available, forge.forgeAvailable(4));
    }

    @Test
    public void testForge(){
        inventory.setTreasury(Resources.GOLD, 100);
        for(int i =0; i < 6; i++){
            DiceSide side = new DiceSide(1, Resources.GOLD);
            forge.forge(dice, side, diceSides.get(i),1);
        }
        for(int i = 0; i <6;i++){
            System.out.println("result : " + result.get(i).getValue() + " " + result.get(i).getType());
            System.out.println("diceSides : " +dice.getDiceSides().get(i).getValue() + " " + dice.getDiceSides().get(i).getType());
        }
        for(int i =0 ; i <6; i++){
            assertEquals(1, dice.getDiceSides().get(i).getValue());
            assertEquals(Resources.GOLD, dice.getDiceSides().get(i).getType());
        }
    }
}
