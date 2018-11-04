package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Highest;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;

/**
 * Pika
 */
public class Rem extends Player {

    Exploit exploit;

    public Rem() {
        super("Rem");
    }

    @Override
    public void setup() {
        exploit = new Highest(name);
    }

    @Override
    public void play(Board boardView) {
        Card card = exploit.compute(boardView);
        if (boardView.playCard(card, name)) {
            logger.debug("le bot '" + name + "' a fait un exploit et a obtenue " + card.getVictoryPoint() + " "
                    + Resources.VICTORY_POINT);
        }
    }

}