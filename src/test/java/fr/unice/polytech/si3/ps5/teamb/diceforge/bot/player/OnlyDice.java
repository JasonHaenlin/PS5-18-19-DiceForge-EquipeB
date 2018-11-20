package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;

/**
 * OnlyDice
 */
public class OnlyDice extends Player {

    public OnlyDice() {
        super("onlyDice");
    }

    @Override
    protected void setup() {

    }

    @Override
    public void play() {

    }

    @Override
    public int callBackDice() {
        return 0;
    }

}