package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.SimpleCard;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.ActionForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class Board {

    private List<Card> moonCards;
    private List<Card> sunCards;

    private Map<String, Integer> playerRegistered;
    private Map<String, Inventory> playerInventory;

    private ActionForge forge;
    private Config conf;

    protected Board(Config conf) {
        playerRegistered = new HashMap<>();
        this.conf = conf;
    }

    protected void initialize() {
        createCard();
        createInventory();
        forge = new ActionForge();
    }

    private void createInventory() {
        playerInventory = new HashMap<>();
        playerRegistered.forEach((name, integer) -> playerInventory.put(name,
                new Inventory(conf.getInvConfig(), conf.getDice1Config(), conf.getDice2Config())));
    }

    protected boolean registrationToBoard(String player, int token) {
        if (playerRegistered.containsKey(player)) {
            return false;
        }
        playerRegistered.put(player, token);
        return true;
    }

    private void createCard() {
        moonCards = new ArrayList<>();
        sunCards = new ArrayList<>();
        for (int moon = 2; moon < 6; moon++) {
            moonCards.add(new SimpleCard(moon, 0, 5 + moon));
        }
        for (int sun = 2; sun < 5; sun++) {
            sunCards.add(new SimpleCard(0, sun, 5 + sun));
        }
    }

    protected List<Card> getEligibleCards(int moonBank, int sunBank) {
        ArrayList<Card> buyable = new ArrayList<>();
        for (Card Card : moonCards) {
            if (Card.getMoonStone() <= moonBank) {
                buyable.add(Card);
            }
        }
        for (Card Card : sunCards) {
            if (Card.getSunStone() <= sunBank) {
                buyable.add(Card);
            }
        }
        buyable.sort((Card a1, Card a2) -> Integer.compare(a1.getMoonStone() + a1.getSunStone(),
                a2.getMoonStone() + a2.getSunStone()));

        return buyable.isEmpty() ? Collections.emptyList() : buyable;
    }

    List<DiceSide> getEligibleSides(int gold) {
        return forge.forgeAvailable(gold);
    }

    void forge(String player, int number, DiceSide sideRemove, DiceSide sideAdd, int cost) {
        Inventory inv;
        inv = playerInventory.get(player);

        Resources res = sideAdd.getType();
        int value = sideAdd.getValue();

        List<DiceSide> available = getEligibleSides(cost);
        List<Resources> listRes = new ArrayList<>();
        List<Integer> listValue = new ArrayList<>();
        for (DiceSide side : available) {
            listRes.add(side.getType());
            listValue.add(side.getValue());
        }
        // Verif if we can buy the side
        if (listRes.contains(res) && listValue.contains(value) && inv.removeResourceFromBag(cost, Resources.GOLD)) {
            inv.getDice(number).setDiceSides(sideRemove, sideAdd);

        }
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
        int sun = inv.getResource(Resources.SUN_STONE);
        return getEligibleCards(moon, sun);
    }

    public Dice getDice(String player, int number) {
        return playerInventory.get(player).getDice(number);
    }

    public Map<Resources, Integer> rolldice(String name) {
        return playerInventory.get(name).rolldice();
    }

}