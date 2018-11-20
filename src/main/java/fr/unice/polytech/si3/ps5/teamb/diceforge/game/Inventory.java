package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * inventory of the game, it contains the dices, the played cards and the
 * current resources of the player
 */
public class Inventory {

    private static Logger logger = LogManager.getLogger(Inventory.class);

    private Map<Resources, Integer> treasury = new EnumMap<>(Resources.class);
    private List<Dice> dices = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    private int lastUpdate = 0;

    /**
     * create a new empty inventory with basics dices
     */
    public Inventory() {
        for (Resources rsc : Resources.values()) {
            treasury.put(rsc, 0);
        }
        dices.add(new Dice());
        dices.add(new Dice());
    }

    /**
     * create an inventory using the config json file
     * 
     * @param invConfig   inventory configuration
     * @param dice1Config first dice configuration
     * @param dice2Config second dice configuration
     */
    public Inventory(EnumMap<Resources, Integer> invConfig, List<DiceSide> dice1Config, List<DiceSide> dice2Config) {
        this.treasury = invConfig.clone();
        dices.add(new Dice(dice1Config));
        dices.add(new Dice(dice2Config));
    }

    /**
     * roll the selected dice and return the side (number can be 0 or 1)
     * 
     * @param number
     * @return the dice side
     */
    public DiceSide rolldice(int number) {
        if (number > dices.size() - 1 || number < 0)
            return new DiceSide(0, Resources.GOLD);
        return dices.get(number).roll();
    }

    /**
     * roll the player dices and update the bag
     * 
     * @return the resources got by the dices
     */
    Map<Resources, Integer> rolldices() {
        Map<Resources, Integer> newResources = new HashMap<>();
        dices.forEach(dice -> {
            logger.trace(dice.toString());
            DiceSide side = dice.roll();
            updateResources(newResources, side);
        });
        return newResources;
    }

    private void updateResources(Map<Resources, Integer> newResources, DiceSide side) {
        addResourceToBag(side.getValue(), side.getType());
        if (side.getType().equals(Resources.VICTORY_POINT)) {
            lastUpdate += side.getValue();
        }
        if (newResources.containsKey(side.getType()))
            newResources.replace(side.getType(), newResources.get(side.getType()) + side.getValue());
        else
            newResources.put(side.getType(), side.getValue());
    }

    /**
     * add resources to the bag
     * 
     * @param amout resource quantity
     * @param res   [gold, victory point, sun stone, moon stone]
     */
    void addResourceToBag(int amout, Resources res) {
        treasury.replace(res, treasury.get(res) + amout);
    }

    /**
     * remove resources from the player bag
     * 
     * @param amout resource quantity
     * @param res   [gold, victory point, sun stone, moon stone]
     * @return true if the player had enough resources in his bag
     */
    boolean removeResourceFromBag(int amout, Resources res) {
        if (treasury.get(res) < amout) {
            return false;
        }
        treasury.replace(res, treasury.get(res) - amout);
        return true;
    }

    /**
     * add a played card in the back
     * 
     * @param card
     * @return true if the bag had enough resources to get the card
     */
    boolean addCardToBag(Card card) {
        cards.add(card);
        lastUpdate = card.getVictoryPoints();
        addResourceToBag(card.getVictoryPoints(), Resources.VICTORY_POINT);

        return !(!removeResourceFromBag(card.getMoonStone(), Resources.MOON_STONE)
                || !removeResourceFromBag(card.getSunStone(), Resources.SUN_STONE));

    }

    /**
     * get resources
     * 
     * @param rsc
     * @return
     */
    int getResource(Resources rsc) {
        return treasury.get(rsc);
    }

    /**
     * get the last victory point and put back the value to 0 to prepare for futher
     * update
     * 
     * @return
     */
    public int pollLastVictoryPoint() {
        int point = lastUpdate;
        lastUpdate = 0;
        return point;
    }

    /**
     * get the dices
     * 
     * @param number
     * @return
     */
    Dice getDice(int number) {
        return dices.get(number);
    }

    /**
     * replace a side on the selected dice with a new one
     * 
     * @param diceNumber
     * @param sideToRemove
     * @param sideToAdd
     * @return true if the side has been correctly replaced, false otherwise
     */
    boolean replaceDiceSide(int diceNumber, DiceSide sideToRemove, DiceSide sideToAdd) {
        if (diceNumber >= dices.size()) {
            return false;
        }
        int cost = sideToAdd.getCost();
        Dice dice = dices.get(diceNumber);
        List<DiceSide> side = dice.retrieveCurrentSides();
        for (int i = 0, n = dice.size(); i < n; i++) {
            if (side.get(i).equals(sideToRemove)) {
                side.remove(i);
                side.add(sideToAdd);
                removeResourceFromBag(cost, Resources.GOLD);
                return true;
            }
        }
        return false;
    }

    public Card peekLastCard() {
        return cards.get(cards.size() - 1);
    }
}