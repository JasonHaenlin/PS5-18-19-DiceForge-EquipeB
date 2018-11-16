package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

import java.util.List;

public class SingleRessource extends Forge {
	private int diceToForge;

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
		return null;
	}

	/**
	 * Calculates and return the value of a dice depending on the resource to forge on it.
	 * The higher the expected value of the resource to forge, the higher the score.
	 * The higher the expected value of another resource on a face, the lower the score gets.
	 * In this method, when the ressource isn't the one forged, values are scaled :
	 * GloryPoint>SunStone>MoonStone>Gold
	 * @param diceSides
	 * @return an int equal to the score given to the dice
	 */
	public int computeDiceValue(List<DiceSide> diceSides) {
		int diceValue = 0;
		for (DiceSide side : diceSides) {
			if (side.getType() == resource){
				diceValue += side.getValue()*100;
			} else {
				diceValue += side.getValue();
			}
		}
		return diceValue;
	}

	/**
	 * Sets the field diceToForge to the best dice to forge. (0 or 1)
	 * @param diceSides0 corresponds to the first dice's sides to compare
	 * @param diceSides1 corresponds to the second dice's sides to compare
	 */
	void setDiceToForge(List<DiceSide> diceSides0, List<DiceSide> diceSides1) {
		int valueOfDice0 = computeDiceValue(diceSides0);
		int valueOfDice1 = computeDiceValue(diceSides1);
		if (valueOfDice0 != valueOfDice1) {
			this.diceToForge = (valueOfDice0 > valueOfDice1) ? 0 : 1;
		} else {
			this.diceToForge = 0;
		}
	}


	/**
	 * Gives the better dice to get forged, depends on the resource declared in the field resource.
	 * @return an int equals to the number of the dice to get forged.
	 */
	int getDiceToForge() {
		return diceToForge;
	}

}
