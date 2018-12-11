package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideMultiplyResource
 * 
 * @see Instructions
 */
public class SideMultiplyResource extends DiceSide {

    // not 3 because it will just add 2 more instructions to have 3
    // in totals
    private final static int MULTIPLICATIVE_COEFF = 2;

    public SideMultiplyResource(int cost) {
        super(cost, "SideMultiplyResource");
    }

    @Override
    public void setAllInstructions(List<Instructions> inst, List<Instructions> postInst) {
        inst.add(new Instructions() {
            @Override
            public TuplePair<Resources, Integer> execution(DiceSide secondary, Player player) {
                if (secondary == null || name.equals(secondary.name)) // don't like infinit loop XD
                    return new TuplePair<Resources, Integer>(Resources.VICTORY_POINT, 0);
                List<Instructions> it = secondary.getInstructions();
                for (int i = 0; i < MULTIPLICATIVE_COEFF; i++) {
                    postInst.addAll(it);
                }
                return new TuplePair<Resources, Integer>(Resources.VICTORY_POINT, 0);
            }
        });
    }

    @Override
    public boolean contains(Resources res) {
        return false;
    }

    @Override
    public int coefficient() {
        return 0;
    }

    @Override
    public String toString() {
        return "[X " + MULTIPLICATIVE_COEFF + "]";
    }

}