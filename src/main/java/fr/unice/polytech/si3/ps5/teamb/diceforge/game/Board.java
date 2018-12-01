package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Islands;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Temple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Guard;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Tuple;

/**
 * The board regroup all the inteith the game items
 */
public class Board {

    private static final int FORGE = 1;
    private static final int EXPLOIT = 2;

    private static Logger logger = LogManager.getLogger(Board.class);

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
        playerInventory = new LinkedHashMap<>();
        EnumMap<Resources, Integer> inv = conf.getInvConfig();
        inv.put(Resources.GOLD, inv.get(Resources.GOLD) + 1);

        for (String name : playerRegistered) {
            inv.put(Resources.GOLD, inv.get(Resources.GOLD) - 1);
            playerInventory.put(name, new Inventory(inv, conf.getDice1Config(), conf.getDice2Config()));
        }
    }

    /**
     * register a new player on the current game
     * 
     * @param player name
     * @param token  secret passed by the game
     * @return true if the player has been correctly added, false otherwise when the
     *         player is already in the board
     */
    protected boolean registrationToBoard(String player) {
        if (playerRegistered.contains(player))
            return false;
        return playerRegistered.add(player);
    }

    protected boolean temporaryAuthorization(String player) {
        logger.trace(playerInventory.get(player).toString());
        temple.resetTurn();
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
    public Map<Resources, Integer> rolldice(Player player) {
        return playerInventory.get(player.toString()).rolldices(player);
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
     * Forge *
     * 
     * @param player       name
     * @param diceNumber   where to forge the new face. On the first or second dice
     *                     (0 or 1)
     * @param sideToRemove side to be replace
     * @param sideToAdd    new side
     * @return true if successfully forge
     */
    public boolean forge(String player, int diceNumber, DiceSide sideToRemove, DiceSide sideToAdd) {
        Inventory inv = playerInventory.get(player);
        if (sideToAdd == null || sideToRemove == null || !guard.isAuthorizated(player, FORGE)) {
            return false;
        }
        if (!inv.hasEnoughResources(sideToAdd.getCost(), Resources.GOLD)) {
            return false;
        }
        if (!temple.removeSide(sideToAdd)) {
            return false;
        }
        if (inv.replaceDiceSide(diceNumber, sideToRemove, sideToAdd)) {
            guard.revokeAuthorizationPartially(player, EXPLOIT);
            logger.debug("Le bot '" + player + "' a forge et a obtenu une face " + sideToAdd.toString());
            logger.trace(inv.toString());
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
     * retrieve the playable cards with an option to substract a resources to get
     * les options or to play multiple times by paying less
     * 
     * @param player            name
     * @param rsc               selected resources
     * @param amountToSubstract (need to be > 0)
     * @return
     */
    public List<Card> playableCards(String player, Resources rsc, int amountToSubstract) {
        Inventory inv = playerInventory.get(player);
        int sun = inv.getResource(Resources.SUN_STONE);
        int moon = inv.getResource(Resources.MOON_STONE);
        if (amountToSubstract > 0)
            switch (rsc) {
            case SUN_STONE:
                sun -= amountToSubstract;
                break;
            case MOON_STONE:
                moon -= amountToSubstract;
                break;
            default:
                break;
            }
        return islands.getBuyableCards(moon, sun);
    }

    /**
     * Exploit *
     * 
     * @param card   to be played
     * @param player name
     * @return true if the card has been played
     */
    public boolean exploit(Card card, String player) {
        Inventory inv = playerInventory.get(player);
        if (card == null || !guard.isAuthorizated(player, 2)) {
            return false;
        }
        if (!(inv.hasEnoughResources(card.getMoonStone(), Resources.MOON_STONE)
                && inv.hasEnoughResources(card.getSunStone(), Resources.SUN_STONE))) {
            return false;
        }
        if (!islands.removeCard(card)) {
            return false;
        }
        if (inv.addCardToBag(card)) {
            guard.revokeAuthorization();
            logger.debug("Le bot '" + player + "' a fait un exploit et a obtenu " + card.getVictoryPoints() + " "
                    + Resources.VICTORY_POINT);
            logger.trace(inv.toString());
            return true;
        }
        return false;
    }

    /**
     * play the last card exploit by the bot
     * 
     * @param bot
     */
    void playLastCard(Player bot) {
        if (bot.toString().equals(guard.peekLastPlayer())) {

            Inventory inv = playerInventory.get(bot.toString());
            Card card = inv.peekLastCard();
            // don't play the card again if the card has been already used
            if (card == null)
                return;
            if (card.hasBeenPlayed())
                return;

            // Card effect
            logger.debug("Le bot '" + bot.toString() + "' joue la carte " + card.toString());
            card.setCardOwner(bot);
            card.playImmEffect(inv);
            card.hasAfterEffect(inv);
            card.hasToken(inv);
            card.hasResourcesToStore().forEach((r, n) -> inv.addResourceToBag(n, r));
            logger.trace(inv.toString());

        }
    }

    /**
     * Returns a list of sides corresponding to the player's dice selected. Example
     * getDiceSide("Cloud",0) returns Cloud's first dice's sides.
     * 
     * @param player the name of the player as a String.
     * @param number the number of the dice to get (0 or 1)
     * @return the list of sides of a the specified player's dice.
     */
    public List<DiceSide> getDiceSide(String player, int number) {
        return playerInventory.get(player).getDice(number).getDiceSides();
    }

    /**
     * Return the inventory for the selected player.
     * 
     * @param player
     * @return inventory map
     */
    public List<Tuple> peekInventory(String player) {
        List<Tuple> tmp = new ArrayList<>();
        Inventory inv = playerInventory.get(player);
        tmp.add(new Tuple(Resources.GOLD, inv.getResource(Resources.GOLD), inv.getGoldLim()));
        tmp.add(new Tuple(Resources.SUN_STONE, inv.getResource(Resources.SUN_STONE), inv.getSunLim()));
        tmp.add(new Tuple(Resources.MOON_STONE, inv.getResource(Resources.MOON_STONE), inv.getMoonLim()));
        return tmp;
    }

    /**
     * 
     * @param player
     * @return
     */
    public boolean isPlayingAgainPossible(String player) {
        int sunStone = playerInventory.get(player).getResource(Resources.SUN_STONE);
        return sunStone >= 2;
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