package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

/**
 * Player
 */
public interface Player {

    /**
     * create and config bot strategies
     */
    void setup();

    /**
     * make a new move based on the updated board
     */
    void play(Board boardView);
}