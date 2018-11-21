package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

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
        if (boardView.exploit(card, name)) {
            logger.debug("le bot '" + name + "' a fait un exploit et a obtenu " + card.getVictoryPoints() + " "
                    + Resources.VICTORY_POINT);
        }
    }

    @Override
    public int callBackDice() {
        return 0;
    }

}