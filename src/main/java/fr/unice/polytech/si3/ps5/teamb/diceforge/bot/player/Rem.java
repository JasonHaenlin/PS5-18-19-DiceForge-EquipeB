package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.HighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

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
        exploit = new Exploit(name, boardView);
    }

    @Override
    protected void play() {
        exploit.compute(new HighestExploit());
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