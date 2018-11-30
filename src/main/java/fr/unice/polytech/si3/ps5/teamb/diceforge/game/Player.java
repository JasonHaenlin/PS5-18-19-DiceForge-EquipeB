package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;

/**
 * Player class to be use to create a bot for the game
 */
public abstract class Player {

    protected static Logger logger = LogManager.getLogger(Player.class);
    protected Board boardView;
    protected Manager manager;
    protected Integer gameRound;
    protected Forge forge;
    protected Exploit exploit;

    private static int countInstance = 0;

    protected String name;

    /**
     * create a new player for the game
     * 
     * @param name of the bot
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
    protected abstract void play();

    /**
     * override this methode if you want to use the possibiliy to play again
     */
    protected boolean replayOnceAgain() {
        return false;
    }

    public abstract int callBackDice();

    public abstract Resources callBackResources(List<Resources> res);

    void addBoard(Integer gameRound, Board boardView) {
        this.boardView = boardView; // TODO will not it anymore
        this.forge = new Forge(name, boardView);
        this.exploit = new Exploit(name, boardView);
        this.manager = new Manager(boardView);
        this.gameRound = gameRound;
    }

    @Override
    public String toString() {
        return name;
    }

}