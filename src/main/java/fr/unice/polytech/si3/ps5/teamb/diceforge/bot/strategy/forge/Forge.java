package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.List;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;


/**
 * Forge
 */
public abstract class Forge {

    protected final String id;
    protected final Resources resource;
    private int randomDice;
    private int diceToForge;


    protected Forge(String id, Resources resource) {
        this.id = id;
        this.resource = resource;
    }

    public abstract DiceSide compute(List<DiceSide> feasible);

    public DiceSide removableDiceSide(List<DiceSide> dice0, List<DiceSide> dice1, Resources resources) { //TODO enlever ressources
        randomDice = new Random().nextInt(2);
        if (randomDice == 0) {
            for (DiceSide diceside : dice0) {
                if (diceside.getType().ordinal() == 0 || diceside.getType().ordinal() == 1) {
                    return diceside;
                }
            }
        } else {
            for (DiceSide diceside : dice1) {
                if (diceside.getType().ordinal() == 0 || diceside.getType().ordinal() == 1) {
                    return diceside;
                }
            }
        }
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
    private int computeDiceValue(List<DiceSide> diceSides) {
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
    public void setDiceToForge(List<DiceSide> diceSides0, List<DiceSide> diceSides1) {
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
    public int getDiceToForge() {
        return diceToForge;
    }

    public Resources getResource() {
        return resource;
    }
}