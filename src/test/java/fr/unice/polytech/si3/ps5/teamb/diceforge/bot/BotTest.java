package fr.unice.polytech.si3.ps5.teamb.diceforge.bot;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.Forge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.VictoryPoint;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;

public class BotTest extends Player {

    Forge forge;

    public BotTest() {
        super("BotTest");
    }

    @Override
    public void setup() {
        forge = new VictoryPoint(name);
    }

    @Override
    public void play(Board boardView) {
        List<DiceSide> feasible = new ArrayList<>(boardView.getEligibleSides(name));
        DiceSide side = forge.compute(feasible);
        logger.trace(side);
        if (boardView.forge(name, 0, boardView.getDice(name, 0).getDiceSides().get(0), side)) {
            logger.debug("le bot '" + name + "' a forge et a obtenue " + side.toString());
        }
    }

}