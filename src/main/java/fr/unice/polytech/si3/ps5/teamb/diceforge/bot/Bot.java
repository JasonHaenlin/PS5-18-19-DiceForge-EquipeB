package fr.unice.polytech.si3.ps5.teamb.diceforge.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

/**
 * Create a bot
 *
 * A bot play the game
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 *
 */

public class Bot {

    private static Logger LOGGER = LogManager.getLogger(Bot.class);

    private Resources currRes = Resources.GOLD;
    private int currValue = 0;

    private Dice dice;
    private String name;

    private List<Card> cards = new ArrayList<>();
    Map<Resources, Integer> treasury = new HashMap<>();

    /**
     * constructor
     */

    public Bot(String name) {
        for (Resources rsc : Resources.values()) {
            treasury.put(rsc, 0);
        }
        dice = new Dice();
        this.name = name;
    }

    public void play(Board board) {
        rollDice();
        exploit(board);
    }

    void exploit(Board board) {
        List<Card> feasible = board.getEligibleCards(treasury.get(Resources.MOON_STONE),
                treasury.get(Resources.SUN_STUNE));
        if (feasible.size() != 0) {
            Card card = feasible.get(feasible.size() - 1);
            currValue = card.getVictoryPoint();
            currRes = Resources.VICTORY_POINT;
            cards.add(feasible.get(feasible.size() - 1));
            updateBag(card.getVictoryPoint(), Resources.VICTORY_POINT, true);
            updateBag(card.getSunStone(), Resources.SUN_STUNE, false);
            updateBag(card.getMoonStone(), Resources.MOON_STONE, false);
            LOGGER.debug("Le bot " + this.name + " a fait un exploit et a obtenue " + currValue + " " + currRes);
        }

    }

    private void updateBag(int amout, Resources res, boolean op) {
        if (op)
            treasury.replace(res, treasury.get(res) + amout);
        else
            treasury.replace(res, treasury.get(res) - amout);
        LOGGER.debug(treasury.toString());
    }

    /**
     *
     * @return the number of dice
     */

    private void rollDice() {
        updateCurrentStateValue(dice.random(treasury));
    }

    private void updateCurrentStateValue(DiceSide side) {
        currValue = side.getValue();
        currRes = side.getType();
    }

    public int getVictoryPoint() {
        return treasury.get(Resources.VICTORY_POINT);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public int getLastValue() {
        return currValue;
    }

    public Resources getLastResource() {
        return currRes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Bot)) {
            return false;
        }
        Bot bot = (Bot) obj;
        return this.getVictoryPoint() == bot.getVictoryPoint();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
