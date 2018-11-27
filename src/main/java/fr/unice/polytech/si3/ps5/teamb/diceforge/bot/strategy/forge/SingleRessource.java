package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

import java.util.*;

public class SingleRessource extends Forge {

	public SingleRessource(String id) {
		super(id);
	}

	/**
	 * Returns the best side to forge on, depends on the resource to forge and the
	 * available sides in the temple.
	 * 
	 * @param feasible is the list of sides available in the temple
	 * @param resource
	 * @return
	 */
	@Override
	public DiceSide compute(List<DiceSide> feasible, Resources resource) {
		keepOnlyResource(feasible, resource);
		if (feasible.isEmpty()) {
			return null;
		}
		feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.getValue(), s2.getValue()));
		return feasible.get(feasible.size() - 1);
	}

	private void keepOnlyResource(List<DiceSide> feasible, Resources resource) {
		int size = feasible.size();
		int i = 0;
		while (i < size) {
			if (!(feasible.get(i).getType().equals(resource))) {
				feasible.remove(i);
				size--;
			} else {
				i++;
			}
		}
	}

	@Override
    public int analyseDice(List<DiceSide> diceSides, Resources resources){
	    int potential = 0;
	    for(DiceSide diceSide: diceSides){
	        if(diceSide.getType() == resources){
	            potential += diceSide.getValue();
            }
        }
        return potential;
    }

	/**
	 * Chose the dice for forge (select the dice where analyseDice doesn't return
	 * the resource like sun or moon stone) par default, select the dice with gold
	 * or the Dice number 1 We can't chose resource = gold
	 * 
	 * @param diceSides0, diceSides1, Resources
	 */
	@Override
    public int choseDice(List<DiceSide> diceSides0, List<DiceSide> diceSides1, Resources resources){
	    //TODO
        return 0;
    }

    @Override //TODO change the method to fix the parameter resources which is not used
	public DiceSide choseSideRemove(List<DiceSide> dicesSides, Resources resources) {
		List<DiceSide> potentielSides = new ArrayList<>();
		for (DiceSide side : dicesSides) {
			if (side.getType() == Resources.GOLD) {
				potentielSides.add(side);
			}
		}
		if (potentielSides.size() == 0)
			return null;
		for (int i = 0; i <= 6; i++) { // Récupère la valeur min
			for (DiceSide side : potentielSides) {
				if (side.getValue() == i)
					return side;
			}
		}
		return null;
	}

}
