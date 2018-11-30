package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;

public interface State {

    void init(Board boardView);

    boolean condition(Board boardView);

    void otherwise(Board boardView);

    void doAction(Board boardView);

}