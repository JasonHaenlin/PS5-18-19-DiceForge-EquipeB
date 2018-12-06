package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideEnum
 */
public enum SideEnum {

    SIMPLE_SIDE("Simple Side") {
        @Override
        public DiceSide build(JSONArray obj, int cost) {
            Resources r = obj.getJSONObject(0).getEnum(Resources.class, "resource");
            int n = obj.getJSONObject(0).getInt("amount");
            return new SideSimple(r, n, cost);
        }
    },
    ADD_MULTIPLE_SIDE("Multiple Resources Side") {
        @Override
        public DiceSide build(JSONArray obj, int cost) {
            List<TuplePair<Resources, Integer>> sides = extractArrayObj(obj);
            return new SideAddMultiple(sides, cost);
        }
    },
    CHOICE_SIDE("Choice side") {
        @Override
        public DiceSide build(JSONArray obj, int cost) {
            List<TuplePair<Resources, Integer>> sides = extractArrayObj(obj);
            return new SideChoise(sides, cost);
        }
    },
    MULTIPLY_SIDE("Multiply Resource Side") {
        @Override
        public DiceSide build(JSONArray obj, int cost) {
            return new SideMultiplyResource(cost);
        }
    };

    public abstract DiceSide build(JSONArray obj, int cost);

    private String name;

    SideEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    protected List<TuplePair<Resources, Integer>> extractArrayObj(JSONArray obj) {
        List<TuplePair<Resources, Integer>> sides = new ArrayList<>();
        for (Object elem : obj) {
            JSONObject jsn = (JSONObject) elem;
            sides.add(
                    new TuplePair<Resources, Integer>(jsn.getEnum(Resources.class, "resource"), jsn.getInt("amount")));
        }
        return sides;
    }

}