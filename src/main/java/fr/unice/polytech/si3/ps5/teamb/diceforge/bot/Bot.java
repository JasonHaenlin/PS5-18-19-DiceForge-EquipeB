package fr.unice.polytech.si3.ps5.teamb.diceforge.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;

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
        List<Card> feasible = board.getEligibleCards(treasury.get(Resources.SUN_STUNE),
                treasury.get(Resources.MOON_STONE));
        if (feasible.size() != 0) {
            cards.add(feasible.get(feasible.size() - 1));
            updateBag(feasible, Resources.VICTORY_POINT);
            updateBag(feasible, Resources.SUN_STUNE);
            updateBag(feasible, Resources.MOON_STONE);
        }

    }

    private void updateBag(List<Card> feasible, Resources res) {
        treasury.replace(res, treasury.get(res) + feasible.get(feasible.size() - 1).getVictoryPoint());
    }

    /**
     *
     * @return the number of dice
     */

    private void rollDice() {
        dice.random(treasury);
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

    public int getLastValue() {
        return currValue;
    }

    public Resources getLastResource() {
        return currRes;
    }
}
