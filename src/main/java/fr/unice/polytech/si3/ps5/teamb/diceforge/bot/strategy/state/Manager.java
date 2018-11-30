package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

import java.util.LinkedList;
import java.util.Queue;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;

/**
 * Manager
 */
public class Manager {

    Context context;
    Queue<State> template;
    Board boardView;
    boolean initState;

    public Manager(Board boardView) {
        this.context = new Context();
        this.template = new LinkedList<>();
        this.boardView = boardView;
        this.initState = false;
    }

    public Manager addTemplate(State state) {
        this.template.add(state);
        context.setState(this.template.peek());
        return this;
    }

    public void play() {
        State s = this.template.peek();
        if (!initState)
            initState(s);
        if (s.condition(boardView))
            s.doAction(boardView);
        else
            s.otherwise(boardView);
    }

    private void initState(State s) {
        s.init(boardView);
        initState = true;
    }

    public void nextTemplate() {
        if (this.template.size() > 1) {
            this.template.poll();
            context.setState(this.template.peek());
            initState = false;
        }
    }

}