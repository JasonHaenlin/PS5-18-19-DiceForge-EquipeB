package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.Callback;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.CallbackDiceWithMostResources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.CallbackHammerOptimization;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.callback.CallbackSelectResources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateEarlyGameForgeGoldPriority;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateLateGameExploitHigestCard;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateMidGameHammerPriority;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateMiddleGameForgeMoonSun;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Asta Bot which has for main quest to success in completing the Hammer card
 * currently
 */
public class Asta extends Player {

    private Manager manager;

    public Asta() {
        super("Asta");
    }

    @Override
    protected void setup() {
        manager = new Manager(this);
        // @formatter:off
        manager.addState(new TemplateEarlyGameForgeGoldPriority())
                .addState(new TemplateMidGameHammerPriority())
                .addState(new TemplateMiddleGameForgeMoonSun())
                .addState(new TemplateLateGameExploitHigestCard())
                .build();
        // @formatter:on
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
