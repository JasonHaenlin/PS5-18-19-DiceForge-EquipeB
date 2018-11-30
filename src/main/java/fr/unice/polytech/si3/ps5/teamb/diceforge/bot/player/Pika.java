package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.HighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.HighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.SingleResource;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.State;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Pika
 */
public class Pika extends Player {

    public Pika() {
        super("Pikachu");
    }

    @Override
    protected void setup() {
        // first state
        manager.addTemplate(new State() {

            @Override
            public void init(Board boardView) {
                forge.setdiceTypePriority(Resources.GOLD, Resources.MOON_STONE, Resources.SUN_STONE);
            }

            @Override
            public boolean condition(Board boardView) {
                return gameRound < 3;
            }

            @Override
            public void doAction(Board boardView) {
                forge.compute(new SingleResource(), new HighestForge(), true);
                exploit.compute(new HighestExploit());
            }

            @Override
            public void otherwise(Board boardView) {
                manager.nextTemplate();
            }
        });
        // second state
        manager.addTemplate(new State() {

            @Override
            public void init(Board boardView) {
                forge.setdiceTypePriority(Resources.MOON_STONE, Resources.SUN_STONE);
            }

            @Override
            public boolean condition(Board boardView) {
                return gameRound < 5;
            }

            @Override
            public void doAction(Board boardView) {
                exploit.compute(new HighestExploit());
                forge.compute(new SingleResource(), new HighestForge(), true);
            }

            @Override
            public void otherwise(Board boardView) {
                forge.compute(new SingleResource(), new HighestForge(), true);
                exploit.compute(new HighestExploit());
            }

        });
    }

    @Override
    protected void play() {
        manager.play();
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