package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.Callback;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.CallbackDiceWithMostResources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.CallbackHammerOptimization;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.CallbackSelectResources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateGameFullRandom;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Pika
 */
public class Cloud extends Player {

    Manager manager;

    public Cloud() {
        super("Cloud");
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
    protected boolean replayOnceAgain() {
        return !boardView.playableCards(name, Resources.SUN_STONE, 2).isEmpty();
    }

    @Override
    public int callBackDice() {
        Callback<Integer, Resources> c = new CallbackDiceWithMostResources();
        return c.runCallback(manager.getContext(), Resources.SUN_STONE);
    }

    @Override
    public Resources callBackResources(Map<Resources, Integer> resInt) {
        Callback<Resources, Map<Resources, Integer>> c = new CallbackSelectResources();
        return c.runCallback(manager.getContext(), resInt);
    }

    @Override
    public int callBackHammer(int amount) {
        Callback<Integer, Integer> c = new CallbackHammerOptimization();
        return c.runCallback(manager.getContext(), amount);
    }

}