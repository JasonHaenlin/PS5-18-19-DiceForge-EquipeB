package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

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

    @Override
    public Resources callBackResources(List<Resources> res) {
        return null;
    }

}