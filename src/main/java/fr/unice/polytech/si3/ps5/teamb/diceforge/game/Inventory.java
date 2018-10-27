package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;

/**
 * Inventory
 */
public class Inventory {
    Map<Resources, Integer> treasury = new HashMap<>();
    List<Dice> dices = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    Inventory() {
        for (Resources rsc : Resources.values()) {
            treasury.put(rsc, 0);
        }
        dices.add(new Dice());
        dices.add(new Dice());
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