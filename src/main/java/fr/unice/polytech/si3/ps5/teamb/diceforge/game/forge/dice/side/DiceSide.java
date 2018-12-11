package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * DiceSideBis
 */
public abstract class DiceSide {

    public final String name;
    protected final int cost;
    private List<Instructions> inst;
    private List<Instructions> postInst;

    public DiceSide(int cost, String name) {
        this.cost = cost;
        this.name = name;
        this.inst = new ArrayList<>();
        this.postInst = new ArrayList<>();
    }

    /**
     * add the instruction to be execute. Optionally : you can add some post
     * instructions if something need to be done after the main instructions
     * 
     * @param inst
     * @param postInst
     */
    public abstract void setAllInstructions(List<Instructions> inst, List<Instructions> postInst);

    public abstract boolean contains(Resources res);

    public abstract int coefficient();

    /**
     * @return the inst
     */
    public List<Instructions> getInstructions() {
        inst = new ArrayList<>();
        setAllInstructions(inst, postInst);
        return inst;
    }

    public List<TuplePair<Resources, Integer>> executeInstructions(DiceSide secondary, Player player) {
        inst = new ArrayList<>();
        setAllInstructions(inst, postInst);
        List<TuplePair<Resources, Integer>> tuples = new ArrayList<>();
        for (Instructions it : inst) {
            tuples.add(it.execution(secondary, player));
        }
        // the last instructions migth have create other instructions
        for (Instructions it : postInst) {
            tuples.add(it.execution(secondary, player));
        }
        return tuples;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DiceSide)) {
            return false;
        }
        DiceSide side = (DiceSide) obj;
        return this.hashCode() == side.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.cost);
    }

    public static DiceSide emptySide() {
        return new SideSimple(Resources.GOLD, 0, 0);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}