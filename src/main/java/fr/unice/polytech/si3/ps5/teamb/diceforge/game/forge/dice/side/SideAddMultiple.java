package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.List;
import java.util.Objects;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideAddMulti
 * 
 * @see Instructions
 */
public class SideAddMultiple extends DiceSide {

    List<TuplePair<Resources, Integer>> sides;

    public SideAddMultiple(List<TuplePair<Resources, Integer>> sides, int cost) {
        super(cost, "SideAddMultiple");
        this.sides = sides;
    }

    @Override
    public void setAllInstrucion(List<Instructions> inst, List<Instructions> postInst) {
        sides.forEach(side -> inst.add(new Instructions() {
            @Override
            public TuplePair<Resources, Integer> execution(DiceSide secondary, Player player) {
                return side;
            }
        }));
    }

    @Override
    public boolean contains(Resources res) {
        for (TuplePair<Resources, Integer> t : sides) {
            if (t.type.equals(res))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (TuplePair<Resources, Integer> t : sides) {
            hash += (t.type.ordinal() + t.value);
        }
        return Objects.hash(hash, cost, name);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int coefficient() {
        int res = 0;
        for (TuplePair<Resources, Integer> t : sides) {
            res += t.value;
        }
        return res;
    }

    @Override
    public String toString() {
        return sides.toString();
    }

}