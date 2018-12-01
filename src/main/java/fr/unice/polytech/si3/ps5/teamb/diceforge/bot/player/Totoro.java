package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player;

import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.Exploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.exploit.behaviour.HighestExploit;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.HighestForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.ResourceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;

/**
 * Totoro
 */
public class Totoro extends Player {
    private Forge forge;
    private Exploit exploit;

    public Totoro() {
        super("Totoro");
    }

    @Override
    protected void setup() {
        forge = new Forge(name, boardView);
        exploit = new Exploit(name, boardView);
        forge.setdiceTypePriority(Resources.MOON_STONE);
    }

    @Override
    public void play() {
        forge.compute(new ResourceSide(), new HighestForge(), true);
        exploit.compute(new HighestExploit());
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