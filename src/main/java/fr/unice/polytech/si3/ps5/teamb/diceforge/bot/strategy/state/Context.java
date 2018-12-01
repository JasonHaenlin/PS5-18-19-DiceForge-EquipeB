package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

import java.util.concurrent.atomic.AtomicInteger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;

/**
 * manage the context for the state
 */
public class Context {
    private Template state;
    private final Exploit exploit;
    private final Forge forge;
    private final Board boardView;
    private final Manager manager;
    private AtomicInteger gameRound;
    private final String playerName;

    /**
     * create a new context to instantiate all the elements you need.
     * 
     * @param b       : the bot context
     * @param manager
     */
    Context(Player b, Manager manager) {
        this.exploit = new Exploit(b.toString(), b.getBoardView());
        this.forge = new Forge(b.toString(), b.getBoardView());
        this.gameRound = b.getGameRound();
        this.boardView = b.getBoardView();
        this.manager = manager;
        this.playerName = b.toString();
        this.state = null;
    }

    void setState(Template state) {
        this.state = state;
    }

    Template getState() {
        return state;
    }

    /**
     * @return the exploit
     */
    public Exploit getExploit() {
        return exploit;
    }

    /**
     * @return the forge
     */
    public Forge getForge() {
        return forge;
    }

    /**
     * @return the gameRound
     */
    public int getGameRound() {
        return gameRound.get();
    }

    /**
     * @return the boardView
     */
    public Board getBoardView() {
        return boardView;
    }

    /**
     * @return the manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }
}