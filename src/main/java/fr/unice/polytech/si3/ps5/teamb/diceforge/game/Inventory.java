package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.*;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * Inventory WARNING Method to set resources use only for test
 */
public class Inventory {
    private Map<Resources, Integer> treasury = new HashMap<>();
    private List<Dice> dices = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    private int lastUpdate = 0;

    // Public for test
    public Inventory() {
        for (Resources rsc : Resources.values()) {
            treasury.put(rsc, 0);
        }
        dices.add(new Dice());
        dices.add(new Dice());
    }

    Map<Resources, Integer> rolldice() {
        Map<Resources, Integer> newResources = new HashMap<>();
        dices.forEach(dice -> {
            DiceSide side = dice.random();
            addResourceToBag(side.getValue(), side.getType());
            if (side.getType().equals(Resources.VICTORY_POINT)) {
                lastUpdate += side.getValue();
            }
            if (newResources.containsKey(side.getType()))
                newResources.replace(side.getType(), newResources.get(side.getType()) + side.getValue());
            newResources.put(side.getType(), side.getValue());
        });
        return newResources;
    }

    void addResourceToBag(int amout, Resources res) {
        treasury.replace(res, treasury.get(res) + amout);
    }

    boolean removeResourceFromBag(int amout, Resources res) {
        if (treasury.get(res) < amout) {
            return false;
        }
        treasury.replace(res, treasury.get(res) - amout);
        return true;
    }

    boolean addCardToBag(Card card) {
        cards.add(card);
        lastUpdate = card.getVictoryPoint();
        addResourceToBag(card.getVictoryPoint(), Resources.VICTORY_POINT);

        return !(!removeResourceFromBag(card.getMoonStone(), Resources.MOON_STONE)
                || !removeResourceFromBag(card.getSunStone(), Resources.SUN_STONE));

    }

    int getResource(Resources rsc) {
        return treasury.get(rsc);
    }

    public int getLastVIctoryPoint() {
        int point = lastUpdate;
        lastUpdate = 0;
        return point;
    }

    Dice getDice(int number) {
        return dices.get(number);
    }

    boolean replaceDiceSide(int diceNumber, DiceSide sideToRemove, DiceSide sideToAdd, int cost) {
        Dice dice = dices.get(diceNumber);
        List<DiceSide> side = dice.getDiceSides();
        for (int i = 0, n = dice.size(); i < n; i++) {
            if (side.get(i).equals(sideToRemove)) {
                side.remove(i);
                side.add(sideToAdd);
                return true;
            }
        }
        return false;
    }
}