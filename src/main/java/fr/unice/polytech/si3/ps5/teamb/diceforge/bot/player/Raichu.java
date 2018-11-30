package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.HighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.HighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.SingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Pikachu Hm... Raichu maintenant ! Il est plus fort qu'avant notre bot <3.
 */
public class Raichu extends Player {

    public Raichu() {
        super("Raichu");
        // super Raichu !!!
    }

    @Override
    protected void setup() {
        manager.addState(new Template() { // first state
            @Override
            public void onInitialization(Board boardView) {
                forge.setdiceTypePriority(Resources.GOLD, Resources.MOON_STONE, Resources.SUN_STONE);
            }

            @Override
            public boolean onCondition(Board boardView) {
                return gameRound < 3;
            }

            @Override
            public void doAction(Board boardView) {
                forge.compute(new SingleResource(), new HighestForge(), true);
                exploit.compute(new HighestExploit());
            }

            @Override
            public void doElse(Board boardView) {
                manager.nextTemplate();
            }
        }).addState(new Template() { // second state
            @Override
            public void onInitialization(Board boardView) {
                forge.setdiceTypePriority(Resources.MOON_STONE, Resources.SUN_STONE);
            }

            @Override
            public boolean onCondition(Board boardView) {
                return gameRound < 5;
            }

            @Override
            public void doAction(Board boardView) {
                forge.compute(new SingleResource(), new HighestForge(), true);
                exploit.compute(new HighestExploit());
            }

            @Override
            public void doElse(Board boardView) {
                exploit.compute(new HighestExploit());
                forge.compute(new SingleResource(), new HighestForge(), true);
            }
        }).build();
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
    public Resources callBackResources(List<Resources> res) {
        return null;
    }

    @Override
    protected boolean replayOnceAgain() {
        return !boardView.playableCards(name, Resources.SUN_STONE, 2).isEmpty();
    }

}