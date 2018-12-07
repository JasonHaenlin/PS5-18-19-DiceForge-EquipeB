package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.BlacksmithHammer;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.TuplePair;

/**
 * inventory of the game, it contains the dices, the played cards and the
 * current resources of the player
 */
public class Inventory {

    private static Logger logger = LogManager.getLogger(Inventory.class);

    private Queue<BlacksmithHammer> hammerQueue; // Queue for the hammer
    private Map<Resources, Integer> treasury;
    private List<Dice> dices;
    private List<Card> cards;

    private int lastUpdate = 0;

    private int goldLim = 12;
    private int sunLim = 6;
    private int moonLim = 6;

    /**
     * create a new empty inventory object
     */
    public Inventory() {
        this.treasury = new EnumMap<>(Resources.class);
        this.dices = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.hammerQueue = new LinkedList<>();
    }

    /**
     * create an inventory using the config json file
     * 
     * @param invConfig   inventory configuration
     * @param dice1Config first dice configuration
     * @param dice2Config second dice configuration
     */
    public Inventory(Map<Resources, Integer> invConfig, List<DiceSide> dice1Config, List<DiceSide> dice2Config) {
        this();
        this.treasury.putAll(invConfig);
        dices.add(new Dice(dice1Config));
        dices.add(new Dice(dice2Config));
    }

    /**
     * create a new empty inventory with basics dices
     */
    public void basicSet() {
        for (Resources rsc : Resources.values()) {
            treasury.put(rsc, 0);
        }
        List<DiceSide> diceSides = new ArrayList<>();
        diceSides.add(new SideSimple(Resources.VICTORY_POINT, 2, 0));
        diceSides.add(new SideSimple(Resources.SUN_STONE, 2, 0));
        diceSides.add(new SideSimple(Resources.MOON_STONE, 2, 0));
        diceSides.add(new SideSimple(Resources.GOLD, 1, 0));
        diceSides.add(new SideSimple(Resources.GOLD, 1, 0));
        diceSides.add(new SideSimple(Resources.GOLD, 1, 0));

        dices.add(new Dice(diceSides));
        dices.add(new Dice(diceSides));
    }

    /**
     * roll the selected dice and return the side (number can be 0 or 1)
     * 
     * @param number
     * @return the dice side
     */
    public DiceSide rolldice(int number) {
        if (number > dices.size() - 1 || number < 0)
            return DiceSide.emptySide();
        return dices.get(number).roll();
    }

    /**
     * roll the player dices and update the bag
     * 
     * @return the resources got by the dices
     */
    Map<Resources, Integer> rolldices(Player player) {
        Map<Resources, Integer> newResources = new EnumMap<>(Resources.class);
        List<TuplePair<Resources, Integer>> tuples = new ArrayList<>();
        DiceSide side1 = dices.get(0).roll();
        logger.trace("[DICE] " + dices.get(0).toString());
        DiceSide side2 = dices.get(1).roll();
        logger.trace("[DICE] " + dices.get(1).toString());
        // instructions of the first side
        tuples.addAll(side1.executeInstructions(side2, player));
        // instructions of the second side
        tuples.addAll(side2.executeInstructions(side1, player));
        // udpate the inventory
        updateResources(newResources, tuples);
        return newResources;
    }

    private void updateResources(Map<Resources, Integer> newResources, List<TuplePair<Resources, Integer>> tuples) {
        tuples.forEach(t -> {
            addResourceToBag(t.value, t.type);
            if (newResources.containsKey(t.type))
                newResources.replace(t.type, newResources.get(t.type) + t.value);
            else
                newResources.put(t.type, t.value);
        });
    }

    /**
     * add resources to the bag
     * 
     * @param amount resource quantity
     * @param res    [gold, victory point, sun stone, moon stone]
     */
    void addResourceToBag(int amount, Resources res) {
        amount = checkForHammer(res, amount);
        if (res.equals(Resources.VICTORY_POINT))
            lastUpdate += amount;
        treasury.replace(res, checkResourcesLimit(amount, res));
    }

    private int checkResourcesLimit(int amount, Resources res) {
        int n = treasury.get(res) + amount;
        switch (res) {
        case GOLD:
            return n >= goldLim ? goldLim : n;
        case MOON_STONE:
            return n >= sunLim ? sunLim : n;
        case SUN_STONE:
            return n >= moonLim ? moonLim : n;
        default:
            return n;
        }
    }

    /**
     * remove resources from the player bag
     * 
     * @param amount resource quantity
     * @param res    [gold, victory point, sun stone, moon stone]
     * @return true if the player had enough resources in his bag
     */
    boolean removeResourceFromBag(int amount, Resources res) {
        if (!hasEnoughResources(amount, res)) {
            return false;
        }
        treasury.replace(res, treasury.get(res) - amount);
        return true;
    }

    boolean hasEnoughResources(int amount, Resources res) {
        if (treasury.get(res) < amount) {
            return false;
        }
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
    public final int pollLastVictoryPoint() {
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
    final Dice getDice(int number) {
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
    final boolean replaceDiceSide(int diceNumber, DiceSide sideToRemove, DiceSide sideToAdd) {
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

    /**
     * see the last card
     * 
     * @return
     */
    public final Card peekLastCard() {
        if (cards.isEmpty())
            return null;
        return cards.get(cards.size() - 1);
    }

    /**
     * expand the inventory maximum capacity
     */
    public final void expand(int goldLim, int sunLim, int moonLim) {
        this.goldLim += goldLim;
        this.sunLim += sunLim;
        this.moonLim += moonLim;
    }

    /**
     * add cards with effect that long for more than one turn
     * 
     * @param card
     */
    public final void addHammerEffect(BlacksmithHammer card) {
        if (card != null)
            hammerQueue.add(card);
    }

    public final int checkHammerState() {
        return hammerQueue.peek().goldNeededBeforeCompletion();
    }

    public boolean playerHasHammer(){
        return hammerQueue.isEmpty();
    }

    /**
     * play the hammer effect if an hammer is present
     * 
     * @param res
     * @return
     */
    private int checkForHammer(Resources res, int amount) {
        if (res.equals(Resources.GOLD)) {
            BlacksmithHammer c = hammerQueue.peek();
            if (c != null) {
                amount = c.runHammerEffect(amount);
                if (c.isHammerDone())
                    hammerQueue.poll();
            }
        }
        return amount;
    }

    @Override
    public String toString() {
        String moon = Resources.MOON_STONE.toString() + ":" + getResource(Resources.MOON_STONE);
        String sun = Resources.SUN_STONE.toString() + ":" + getResource(Resources.SUN_STONE);
        String gold = Resources.GOLD.toString() + ":" + getResource(Resources.GOLD);
        return "[BAG] [[" + gold + "], " + "[" + moon + "], " + "[" + sun + "]]";
    }

    /**
     * @return the goldLim
     */
    public int getGoldLim() {
        return goldLim;
    }

    /**
     * @return the moonLim
     */
    public int getMoonLim() {
        return moonLim;
    }

    /**
     * @return the sunLim
     */
    public int getSunLim() {
        return sunLim;
    }
}