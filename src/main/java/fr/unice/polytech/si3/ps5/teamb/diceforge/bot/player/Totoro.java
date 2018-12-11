package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Map;
import java.util.Random;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateGameFullRandom;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Totoro Full random bot...
 */
public class Totoro extends Player {

    Manager manager;

    public Totoro() {
        super("Totoro");
    }

    @Override
    public void setup() {
        manager = new Manager(this);
        manager.addState(new TemplateGameFullRandom()).build();
    }

    @Override
    protected void play() {
        manager.ExecSequence();
    }

    @Override
    public int callBackDice() {
        return new Random().nextInt(2);
    }

    @Override
    public Resources callBackResources(Map<Resources, Integer> resInt) {
        for (Resources key : resInt.keySet()) {
            return key;
        }
        return null;
    }

    @Override
    public int callBackHammer(int amount) {
        return new Random().nextInt(amount);
    }

}