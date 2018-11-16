package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

import java.util.List;

public class SingleRessource extends Forge {


	public SingleRessource(String id, Resources resource) {
		super(id, resource);
	}

	/**
	 * Returns the best side to forge on, depends on the resource to forge and
	 * the available sides in the temple.
	 * @param feasible
	 * @return
	 */
	@Override
	public DiceSide compute(List<DiceSide> feasible) {
		keepOnlyResource(feasible);
		if (feasible.isEmpty()) {
			return null;
		}
		feasible.sort((DiceSide s1, DiceSide s2) -> Integer.compare(s1.getValue(), s2.getValue()));
		return feasible.get(feasible.size() - 1);
	}

	private void keepOnlyResource(List<DiceSide> feasible) {
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



}
