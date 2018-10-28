package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Player
 */
public abstract class Player {

    protected static Logger log = LogManager.getLogger(Player.class);

    protected String name;

    protected Player(String name) {
        this.name = name;
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}