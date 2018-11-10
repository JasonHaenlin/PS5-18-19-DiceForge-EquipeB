package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Player class to be use to create a bot for the game
 */
public abstract class Player {

    protected static Logger logger = LogManager.getLogger(Player.class);

    private static int countInstance = 0;

    protected String name;

    /**
     * create a new player for the game
     * 
     * @param bot name
     */
    protected Player(String name) {
        countInstance++;
        this.name = countInstance + "-" + name;
    }

    /**
     * create and config bot strategies
     */
    protected abstract void setup();

    /**
     * make a new move based on the updated board
     */
    public abstract void play(Board boardView);

    @Override
    public String toString() {
        return name;
    }

}