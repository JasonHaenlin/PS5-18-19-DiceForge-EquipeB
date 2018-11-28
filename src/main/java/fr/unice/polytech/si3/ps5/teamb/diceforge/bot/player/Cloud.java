package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.ResourceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Pika
 */
public class Cloud extends Player {

    private Forge forge;
    private int randomDice = 0;
    private Resources resource;

    public Cloud() {
        super("Cloud");
    }

    @Override
    public void setup() {
        forge = new ResourceSide(name);
        resource = Resources.VICTORY_POINT;
    }

    @Override
    public void play() {
        int numberDice = forge.choseDice(boardView.getDiceSide(name, 0), boardView.getDiceSide(name, 1), null);
        DiceSide side = forge.compute(boardView.playableSides(name), resource);
        boardView.forge(name, randomDice,
                forge.choseSideRemove(boardView.getDiceSide(name, numberDice), Resources.VICTORY_POINT), side);

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