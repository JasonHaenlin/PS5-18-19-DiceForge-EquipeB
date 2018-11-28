package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * OnlyDice
 */
public class OnlyDice2 extends Player {

    public OnlyDice2() {
        super("onlyDice2");
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

    @Override
    public Resources callBackResources(List<Resources> res) {
        return null;
    }

}