package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

/**
 * manage the context for the state
 */
class Context {
    private Template state;

    Context() {
        state = null;
    }

    void setState(Template state) {
        this.state = state;
    }

    Template getState() {
        return state;
    }
}