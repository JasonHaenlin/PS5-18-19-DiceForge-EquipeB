package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.ArrayList;
import java.util.List;

import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

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

class Bot {

    private int victoryPoint;
    private int gold;
    private int sunStone;
    private int moonStone;

    private int lastValue;
    private Resources lastResource;

    private Dice dice;
    private String name;

    private List<Card> cards = new ArrayList<>();

    /**
     * constructor
     */
    Bot() {
        dice = new Dice();
        victoryPoint = 0;
        gold = 0;
        sunStone = 0;
        moonStone = 0;
        name = "bot";
    }

    Bot(String name) {
        dice = new Dice();
        victoryPoint = 0;
        gold = 0;
        sunStone = 0;
        moonStone = 0;
        this.name = name;
    }

    void play(Board board) {
        rollDice();
        getResources();
        exploit(board);

    }

    void exploit(Board board) {
        List<Card> feasible = board.getEligibleCards(moonStone, sunStone);
        if (feasible.size() != 0) {
            cards.add(feasible.get(feasible.size() - 1));
            victoryPoint = victoryPoint + feasible.get(feasible.size() - 1).getVictoryPoint();
            moonStone = moonStone - feasible.get(feasible.size() - 1).getMoonStone();
        }

    }

    int getLastValue() {
        return lastValue;
    }

    Resources getLastResource() {
        return lastResource;
    }

    /**
     *
     * @return the number of dice
     */

    private void rollDice() {
        dice.random();
    }

    private void getResources() {
        lastValue = dice.getRandomValue();
        lastResource = dice.getRandomResources();
        switch (lastResource) {
        case PG:
            victoryPoint += lastValue;
            break;
        case G:
            gold += lastValue;
            break;
        case SS:
            sunStone += lastValue;
            break;
        case MS:
            moonStone += lastValue;
            break;
        }
    }

    int getVictoryPoint() {
        return victoryPoint;
    }

    int getSunStone() {
        return sunStone;
    }

    int getMoonStone() {
        return moonStone;
    }

    int getGold() {
        return gold;
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
}
