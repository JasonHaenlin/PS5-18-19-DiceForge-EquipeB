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
 */
public class Forge {

    private final String name;
    private final Board boardView;
    private List<Resources> resPriority;
    int size;

    public Forge(String name, Board boardView) {
        this.name = name;
        this.boardView = boardView;
    }

    public void setdiceTypePriority(Resources... res) {
        resPriority = new ArrayList<>(Arrays.asList(res));
        size = resPriority.size();
    }

    /**
     * 
     * @param stratD
     * @param stratF
     * @param repeatInf
     */
    public void compute(StratDice stratD, StratForge stratF, boolean repeatInf) {
        List<DiceSide> diceSides0 = boardView.getDiceSide(name, 0);
        List<DiceSide> diceSides1 = boardView.getDiceSide(name, 1);
        int d = stratD.choseDice(diceSides0, diceSides1, null);
        DiceSide sideToRemove;
        DiceSide sideToAdd;
        do {
            sideToAdd = null;
            int i;
            for (i = 0; sideToAdd == null && i < size; i++)
                sideToAdd = stratF.execution(boardView.playableSides(name), resPriority.get(i));
            if (i >= size)
                i = size - 1;
            sideToRemove = stratD.choseSideRemove(boardView.getDiceSide(name, d), resPriority.get(i));
        } while (repeatInf && boardView.forge(name, d, sideToRemove, sideToAdd));
    }
}