package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;

/**
 * Initialize a new template
 * 
 * @author Jason Haenlin
 */
public interface Template {

    /**
     * Correspond to the initialization part when the new state begin This part is
     * compute only one time
     * 
     * @param boardView
     */
    void onInitialization(Board boardView);

    /**
     * This part check the current condition if the condition is true, the
     * <code>doAction()</code> method will be run, the <code>doElse()</code> method
     * otherwise
     * 
     * @param boardView
     * @return true if the condition is valide
     * @see #doAction
     * @see #doElse
     */
    boolean onCondition(Board boardView);

    /**
     * The action you want to do during the turn
     * 
     * @param boardView
     */
    void doAction(Board boardView);

    /**
     * do wathever you want if the condition fail. You can use the
     * <code>nextTemplate()</code> method to compute to the next state
     * 
     * @param boardView
     * @see Manager#nextTemplate()
     */
    void doElse(Board boardView);

}