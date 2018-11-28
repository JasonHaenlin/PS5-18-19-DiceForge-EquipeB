package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.card.Card;

/**
 * Pika
 */
public class Rem extends Player {

    Exploit exploit;

    public Rem() {
        super("Rem");
    }

    @Override
    protected void setup() {
        exploit = new Highest(name);
    }

    @Override
    protected void play() {
        Card card = exploit.compute(boardView.playableCards(name));
        boardView.exploit(card, name);
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