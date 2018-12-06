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
    public void setAllInstrucion(List<Instructions> inst) {
        inst.add(new Instructions() {
            @Override
            public TuplePair<Resources, Integer> execution(DiceSide secondary, Player player) {
                if (secondary == null)
                    return null;
                List<Instructions> inst = secondary.getInstructions();
                for (int i = 0; i < MULTIPLICATIVE_COEFF; i++) {
                    if (!inst.get(i).equals(this)) // don't like infinit loop XD
                        inst.add(inst.get(i));
                }
                return null;
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

}