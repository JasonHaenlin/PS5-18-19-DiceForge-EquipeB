package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Islands;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.ActionForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class Board {

    private Islands islands;

    private Map<String, Integer> playerRegistered;
    private Map<String, Inventory> playerInventory;

    private ActionForge forge;
    private Config conf;

    public Board(Config conf) {
        playerRegistered = new HashMap<>();
        this.conf = conf;
    }

    protected void initialize() {
        createCard();
        createInventory();
        this.forge = new ActionForge(conf.getForgeConfig());
    }

    protected void createInventory() {
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
        this.islands = new Islands(conf.getExploitConfig());
    }

    protected List<DiceSide> getEligibleSides(int gold) {
        return forge.availableSides(gold);
    }

    protected int getVictoryPoint(String name) {
        return playerInventory.get(name).getLastVIctoryPoint();
    }

    protected Board getBoardView() {
        return this;
    }

    public boolean forge(String player, int diceNumber, DiceSide sideToRemove, DiceSide sideToAdd) {
        if (sideToAdd == null || sideToRemove == null) {
            return false;
        }
        if (!forge.removeSide(sideToRemove)) {
            return false;
        }
        return playerInventory.get(player).replaceDiceSide(diceNumber, sideToRemove, sideToAdd);
    }

    public boolean playCard(Card card, String name) {
        // need to add token for further permission
        if (card == null) {
            return false;
        }
        playerInventory.get(name).addCardToBag(card);
        return true;
    }

    public List<DiceSide> getEligibleSides(String name) {
        Inventory inv = playerInventory.get(name);
        int gold = inv.getResource(Resources.GOLD);
        return getEligibleSides(gold);
    }

    public List<Card> getEligibleCards(String name) {
        Inventory inv = playerInventory.get(name);
        int moon = inv.getResource(Resources.MOON_STONE);
        int sun = inv.getResource(Resources.SUN_STONE);
        return islands.getEligibleCards(moon, sun);
    }

    public Dice getDice(String player, int number) {
        return playerInventory.get(player).getDice(number);
    }

    public Map<Resources, Integer> rolldice(String name) {
        return playerInventory.get(name).rolldice();
    }

}