package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

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
        // no specific setup
    }

    @Override
    protected void play() {
        // no strategies are applied to this bot
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