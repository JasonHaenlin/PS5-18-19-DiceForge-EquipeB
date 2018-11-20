package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.ResourceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

public class forgeVP extends Player {

    Forge forge;

    public forgeVP() {
        super("forgeVP");
    }

    @Override
    public void setup() {
        forge = new ResourceSide(name, Resources.VICTORY_POINT);
    }

    @Override
    public void play() {
        List<DiceSide> feasible = new ArrayList<>(boardView.playableSides(name));
        DiceSide side = forge.compute(feasible);
        boardView.forge(name, 0, boardView.getDiceSide(name, 0).get(0), side);

    }

    @Override
    public int callBackDice() {
        return 0;
    }

}