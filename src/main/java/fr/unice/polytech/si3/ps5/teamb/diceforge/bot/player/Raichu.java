package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateEarlyGameForgeGoldPriority;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateLateGameExploitHigestCard;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateMiddleGameForgeMoonSun;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Pikachu Hm... Raichu maintenant ! Il est plus fort qu'avant notre bot <3.
 */
public class Raichu extends Player {

    private Manager manager;

    public Raichu() {
        super("Raichu");
        // super Raichu !!!
    }

    @Override
    protected void setup() {
        manager = new Manager(this);
        // @formatter:off
        manager.addState(new TemplateEarlyGameForgeGoldPriority())
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
    public int callBackDice() {
        return 0;
    }

    @Override
    protected boolean replayOnceAgain() {
        return !boardView.playableCards(name, Resources.SUN_STONE, 2).isEmpty();
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