package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

import java.util.LinkedList;
import java.util.Queue;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;

/**
 * Manager
 * 
 * Create a manager that... manage a state machine with a simple implementation
 * it uses a basic Template with an initialisation, a condition a two action
 * based on the condition result
 * 
 * It's a like a facade
 * 
 * @author Jason haenlin
 * @see Context
 * @see Template
 */
public class Manager {

    private final Context context;
    private final Queue<Template> template;
    private boolean initState;

    public Manager(Player b) {
        this.context = new Context(b, this);
        this.template = new LinkedList<>();
        this.initState = false;
    }

    // @formatter:off
    /**
     * Add a new state with a custom Template. When you are done with your templates
     * You will need to <code>build()</code> it at the end.
     * <p>
     * note : the template exectution are based on the adding order
     * <pre>
     * myManager.addState(template1)
     *          .addState(template2)
     *          .addState(template3)
     *          .build();
     * </pre>
     * @param state
     */
    // @formatter:on
    public Manager addState(Template state) {
        template.add(state);
        return this;
    }

    /**
     * build the state machine when the template are done by using
     * <code>addState()</code>
     */
    public void build() {
        context.setState(template.poll());
    }

    /**
     * run the sequence again. Each call run the a new cycle
     */
    public void ExecSequence() {
        Template s = context.getState();
        if (!initState)
            initState(s);
        if (s.onCondition(context))
            s.doAction(context);
        else
            s.doElse(context);
    }

    /**
     * This method is used to proceed to the next state
     * <p>
     * note : if the next template doesn't existe it will run the
     * <code>doAction</code> to prevent from doing nothing
     */
    public void nextTemplate() {
        if (template.size() >= 1) {
            context.setState(template.poll());
            initState = false;
        } else {
            context.getState().doAction(context);
        }
    }

    private void initState(Template s) {
        s.onInitialization(context);
        initState = true;
    }

    public Context getContext() {
        return context;
    }

}