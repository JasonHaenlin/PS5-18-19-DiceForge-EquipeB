package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;

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
    public void play() {
        // no strategies are applied to this bot
    }

    @Override
    public int callBackDice() {
        return 0;
    }

}