package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

/**
 * Initialize a new template The context contains all you need to do whatever
 * you want like forging or playing a card.
 * 
 * @author Jason Haenlin
 * @see Context
 */
public interface Template {

    /**
     * Correspond to the initialization part when the new state begin This part is
     * compute only one time
     * 
     * @param boardView
     */
    void onInitialization(Context context);

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
    boolean onCondition(Context context);

    /**
     * The action you want to do during the turn
     * 
     * @param boardView
     */
    void doAction(Context context);

    /**
     * do wathever you want if the condition fail. You can use the
     * <code>nextTemplate()</code> method to compute to the next state
     * 
     * @param boardView
     * @see Manager#nextTemplate()
     */
    void doElse(Context context);

}