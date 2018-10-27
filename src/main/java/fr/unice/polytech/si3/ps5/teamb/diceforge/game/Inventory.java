package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * Inventory
 */
public class Inventory {
    private Map<Resources, Integer> treasury = new HashMap<>();
    private List<Dice> dices = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    Inventory() {
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
            if (newResources.containsKey(side.getType()))
                newResources.replace(side.getType(), newResources.get(side.getType()) + side.getValue());
            newResources.put(side.getType(), side.getValue());
        });
        return newResources;
    }

    void addResourceToBag(int amout, Resources res) {
        treasury.replace(res, treasury.get(res) + amout);
    }

    void removeResourceFromBag(int amout, Resources res) {
        treasury.replace(res, treasury.get(res) - amout);
    }

    void addCardToBag(Card card) {
        cards.add(card);
    }

    int getResource(Resources rsc) {
        return treasury.get(rsc);
    }
}