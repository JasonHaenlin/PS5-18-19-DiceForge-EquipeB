package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Islands;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Temple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Guard;

/**
 * The board regroup all the interaction with the game items
 */
public class Board {

    private Islands islands;
    private Temple temple;
    private Guard guard;

    private List<String> playerRegistered;
    private Map<String, Inventory> playerInventory;

    private Config conf;

    /**
     * create a new board for the current game
     * 
     * @param conf of the game
     */
    public Board(Config conf) {
        playerRegistered = new ArrayList<>();
        this.guard = new Guard(2);
        this.conf = conf;
    }

    /**
     * initialize the game for the current registered bot It includes, the card, the
     * inventory of each player and the dice sides
     */
    protected void initialize() {
        createCard();
        createInventory();
        this.temple = new Temple(conf.getForgeConfig());
    }

    /**
     * create the default inventory for each player registered
     */
    protected void createInventory() {
        playerInventory = new HashMap<>();
        playerRegistered.forEach(name -> playerInventory.put(name,
                new Inventory(conf.getInvConfig(), conf.getDice1Config(), conf.getDice2Config())));
    }

    /**
     * register a new player on the current game
     * 
     * @param player name
     * @param token  secret passed by the game
     * @return true if the player has been correctly added, false otherwise when the
     *         player is already in the board
     */
    protected boolean registrationToBoard(String player, int token) {
        if (guard.add(player, token)) {
            playerRegistered.add(player);
            return true;
        }
        return false;
    }

    protected boolean temporaryAuthorization(String player) {
        return guard.enableAuthorization(player);
    }

    /**
     * retrieve the default cards configuration and create the islands
     */
    private void createCard() {
        this.islands = new Islands(conf.getExploitConfig());
    }

    /**
     * roll the dices for the selected player
     * 
     * @param player name
     * @return result of the rolling dices
     */
    public Map<Resources, Integer> rolldice(String player) {
        return playerInventory.get(player).rolldices();
    }

    /**
     * retrieve the playable sides base on the player inventory
     * 
     * @param player
     * @return the playable sides
     */
    public List<DiceSide> playableSides(String player) {
        Inventory inv = playerInventory.get(player);
        int gold = inv.getResource(Resources.GOLD);
        return temple.obtainReplaceableSides(gold);
    }

    /**
     * Forge action
     * 
     * @param player       name
     * @param diceNumber   where to forge the new face. On the first or second dice
     *                     (0 or 1)
     * @param sideToRemove side to be replace
     * @param sideToAdd    new side
     * @return true if successfully forge
     */
    public boolean forge(String player, int diceNumber, DiceSide sideToRemove, DiceSide sideToAdd) {
        // need to add token for further permission
        if (sideToAdd == null || sideToRemove == null || !guard.isAuthorizated(player)) {
            return false;
        }
        if (!temple.removeSide(sideToAdd)) {
            return false;
        }
        if (playerInventory.get(player).replaceDiceSide(diceNumber, sideToRemove, sideToAdd)) {
            guard.removeAuthorization();
            return true;
        }
        return false;
    }

    /**
     * retrieve the playable cards base on the player inventory
     * 
     * @param player name
     * @return
     */
    public List<Card> playableCards(String player) {
        Inventory inv = playerInventory.get(player);
        int moon = inv.getResource(Resources.MOON_STONE);
        int sun = inv.getResource(Resources.SUN_STONE);
        return islands.getBuyableCards(moon, sun);
    }

    /**
     * Exploit action
     * 
     * @param card   to be played
     * @param player name
     * @return true if the card has been played
     */
    public boolean exploit(Card card, String player) {
        // need to add token for further permission
        if (card == null || !guard.isAuthorizated(player)) {
            return false;
        }
        if (!islands.removeCard(card)) {
            return false;
        }
        if (playerInventory.get(player).addCardToBag(card)) {
            guard.removeAuthorization();
            return true;
        }
        return false;
    }

    /**
     * 
     * @param player
     * @param number
     * @return
     */
    public List<DiceSide> getDiceSide(String player, int number) {
        return playerInventory.get(player).getDice(number).getDiceSides();
    }

    /**
     * 
     * @param player
     * @return
     */
    public boolean isPlayingAgainPossible(String player) {
        int sunStone = playerInventory.get(player).getResource(Resources.SUN_STONE);
        if (sunStone >= 2) {
            return true;
        }
        return false;
    }

    /**
     * remove 2 sun fragments if the bot replay the current turn
     * 
     * @param player
     */
    public void removeResourcesToPlayAgain(String player) {
        playerInventory.get(player).removeResourceFromBag(2, Resources.SUN_STONE);
    }

    /**
     * get the last updated victory points
     * 
     * @param player name
     * @return the amount of victory point
     */
    protected int getVictoryPoints(String player) {
        return playerInventory.get(player).pollLastVictoryPoint();
    }

    /**
     * get a view of the board for the player
     * 
     * @return board
     */
    protected Board getBoardView() {
        return this;
    }

}