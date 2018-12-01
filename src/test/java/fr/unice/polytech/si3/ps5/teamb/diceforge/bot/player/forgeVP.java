package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.HighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.ResourceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

public class forgeVP extends Player {

    Forge forge;

    public forgeVP() {
        super("forgeVP");
    }

    @Override
    public void setup() {
        forge = new Forge(name, boardView);
        forge.setdiceTypePriority(Resources.VICTORY_POINT);
    }

    @Override
    public void play() {
        forge.compute(new ResourceSide(), new HighestForge(), false);

    }

    @Override
    public int callBackDice() {
        return 0;
    }

    @Override
    public Resources callBackResources(Map<Resources, Integer> resInt) {
        return null;
    }

    @Override
    public int callBackHammer(int amount) {
        return 0;
    }

}