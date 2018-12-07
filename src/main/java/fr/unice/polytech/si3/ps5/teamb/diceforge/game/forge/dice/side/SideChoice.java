package fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * SideChoise
 * 
 * @see Instructions
 */
public class SideChoice extends DiceSide {

    private Map<Resources, Integer> sides;
    private Resources chosenResource = null;

    public SideChoice(List<TuplePair<Resources, Integer>> sides, int cost) {
        super(cost, "SideChoise");
        this.sides = new EnumMap<>(Resources.class);
        sides.forEach(tuple -> this.sides.put(tuple.type, tuple.value));
    }

    @Override
    public void setAllInstrucion(List<Instructions> inst) {
        inst.add(new Instructions() {
            @Override
            public TuplePair<Resources, Integer> execution(DiceSide secondary, Player player) {
                if (chosenResource == null)// do the callback only one time
                    chosenResource = player.callBackResources(sides);
                return new TuplePair<Resources, Integer>(chosenResource, sides.get(chosenResource));
            }
        });
    }

    @Override
    public boolean contains(Resources res) {
        return sides.containsKey(res);
    }

    @Override
    public int coefficient() {
        int res = 0;
        for (Entry<Resources, Integer> el : sides.entrySet()) {
            res += el.getValue();
        }
        return res / sides.size();
    }

    @Override
    public String toString() {
        return sides.toString();
    }

}