package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.StratForge;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse.StratDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;

/**
 * Forge
 * 
 * @see StratDice
 * @see StratForge
 */
public class Forge {

    private final String name;
    private final Board boardView;
    private List<Resources> resPriority;
    private int size;

    public Forge(String name, Board boardView) {
        this.name = name;
        this.boardView = boardView;
        this.size = 0;
        this.resPriority = new ArrayList<>();
    }

    /**
     * Set the priorities the strategy will try to forge the first resouce first,
     * then the second and so on.
     * 
     * @param res
     */
    public void setdiceTypePriority(Resources... res) {
        resPriority = new ArrayList<>(Arrays.asList(res));
        size = resPriority.size();
    }

    /**
     * 
     * @param stratD    Strategy for the dice
     * @param stratF    Strategy to force
     * @param repeatInf set to true if you want to forge as long as you can
     */
    public void compute(StratDice stratD, StratForge stratF, boolean repeatInf) {
        if (resPriority.isEmpty())
            return;
        List<DiceSide> diceSides0 = boardView.getDiceSide(name, 0);
        List<DiceSide> diceSides1 = boardView.getDiceSide(name, 1);
        DiceSide sideToRemove;
        DiceSide sideToAdd;
        int d;
        do {
            sideToAdd = null;
            int i;
            for (i = 0; sideToAdd == null && i < size; i++)
                sideToAdd = stratF.execution(boardView.playableSides(name), resPriority.get(i));
            if (i >= size)
                i = size - 1;
            d = stratD.choseDice(diceSides0, diceSides1, resPriority.get(i));
            sideToRemove = stratD.choseSideRemove(boardView.getDiceSide(name, d), resPriority.get(i));
        } while (boardView.forge(name, d, sideToRemove, sideToAdd) && repeatInf);
    }
}