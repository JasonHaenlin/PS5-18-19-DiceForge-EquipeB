package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.SimpleCard;

public class Board {

    private List<Card> MoonCards;
    private List<Card> SunCards;

    private Map<String, Integer> playerRegistered;
    private Map<String, Inventory> playerInventory;

    protected Board() {
        playerRegistered = new HashMap<>();
    }

    protected void initialize() {
        createCard();
        createInventory();
    }

    private void createInventory() {
        playerInventory = new HashMap<>();
        playerRegistered.forEach((name, Integer) -> {
            playerInventory.put(name, new Inventory());
        });
    }

    protected boolean registrationToBoard(Player bot) {
        if (playerRegistered.containsKey(bot.toString())) {
            return false;
        }
        playerRegistered.put(bot.toString(), bot.hashCode());
        return true;
    }

    private void createCard() {
        MoonCards = new ArrayList<Card>();
        SunCards = new ArrayList<Card>();
        for (int moon = 2; moon < 6; moon++) {
            MoonCards.add(new SimpleCard(moon, 0, 5 + moon));
        }
        for (int sun = 2; sun < 5; sun++) {
            SunCards.add(new SimpleCard(0, sun, 5 + sun));
        }

    }

    protected List<Card> getEligibleCards(int moonBank, int sunBank) {
        ArrayList<Card> Buyable = new ArrayList<>();
        for (Card Card : MoonCards) {
            if (Card.getMoonStone() <= moonBank) {
                Buyable.add(Card);
            }
        }
        for (Card Card : SunCards) {
            if (Card.getSunStone() <= sunBank) {
                Buyable.add(Card);
            }
        }
        Buyable.sort((Card a1, Card a2) -> Integer.compare(a1.getMoonStone() + a1.getSunStone(),
                a2.getMoonStone() + a2.getSunStone()));

        return Buyable;
    }

    protected int getVictoryPoint(String name) {
        return playerInventory.get(name).getLastVIctoryPoint();
    }

    protected Board getBoardView() {
        return this;
    }

    public boolean playCard(Card card, String name) {
        // need to add token for further permission
        if (card == null) {
            return false;
        }
        playerInventory.get(name).addCardToBag(card);
        return true;
    }

    public List<Card> getEligibleCards(String name) {
        Inventory inv = playerInventory.get(name);
        int moon = inv.getResource(Resources.MOON_STONE);
        int sun = inv.getResource(Resources.SUN_STUNE);
        return getEligibleCards(moon, sun);
    }

    protected void rollAllDices() {
        playerInventory.forEach((name, inv) -> inv.rolldice());
    }

    public Map<Resources, Integer> rolldice(String name) {
        return playerInventory.get(name).rolldice();
    }

}