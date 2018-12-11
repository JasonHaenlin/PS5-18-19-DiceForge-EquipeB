package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.List;
import java.util.Objects;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideSimple
 * 
 * @see Instructions
 */
public class SideSimple extends DiceSide {

    public final TuplePair<Resources, Integer> side;

    public SideSimple(Resources resource, int value, int cost) {
        super(cost, "SideSimple");
        this.side = new TuplePair<>(resource, value);

    }

    @Override
    public void setAllInstructions(List<Instructions> inst, List<Instructions> postInst) {
        inst.add(new Instructions() {
            @Override
            public TuplePair<Resources, Integer> execution(DiceSide secondary, Player player) {
                return side;
            }

        });
    }

    @Override
    public boolean contains(Resources res) {
        return side.type.equals(res);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, cost);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int coefficient() {
        return side.value;
    }

    @Override
    public String toString() {
        return side.toString();
    }

}