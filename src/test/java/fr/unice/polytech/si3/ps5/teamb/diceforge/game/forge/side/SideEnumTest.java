package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.side;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideAddMultiple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideChoice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideEnum;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideMultiplyResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;

/**
 * SideEnumTest
 */
public class SideEnumTest {

    JSONArray obj;

    @Before
    public void setup() {
        obj = new JSONArray();
    }

    @Test
    public void extractSimpleTest() {
        obj.put(new JSONObject().put("resource", "VICTORY_POINT").put("amount", 1));
        DiceSide side = SideEnum.SIMPLE_SIDE.build(obj, 5);
        assertTrue(side instanceof SideSimple);
    }

    @Test
    public void extractMultipleTest() {
        obj.put(new JSONObject().put("resource", "VICTORY_POINT").put("amount", 1))
                .put(new JSONObject().put("resource", "GOLD").put("amount", 5));
        DiceSide side = SideEnum.ADD_MULTIPLE_SIDE.build(obj, 60);
        assertTrue(side instanceof SideAddMultiple);
    }

    @Test
    public void extractChoiceTest() {
        obj.put(new JSONObject().put("resource", "VICTORY_POINT").put("amount", 1))
                .put(new JSONObject().put("resource", "GOLD").put("amount", 5));
        DiceSide side = SideEnum.CHOICE_SIDE.build(obj, 60);
        assertTrue(side instanceof SideChoice);
    }

    @Test
    public void extractMultiplyTest() {
        DiceSide side = SideEnum.MULTIPLY_SIDE.build(null, 5);
        assertTrue(side instanceof SideMultiplyResource);
    }
}