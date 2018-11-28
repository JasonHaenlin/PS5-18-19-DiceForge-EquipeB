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

    protected final String id;
    private final Board boardView;
    private List<Resources> resPriority;

    protected Forge(String id, Board boardView) {
        this.id = id;
        this.boardView = boardView;
    }

    public void setdiceTypePriority(Resources... res) {
        resPriority = new ArrayList<>(Arrays.asList(res));
    }

    public void compute(StratDice stratD, StratForge stratF, boolean repeatInf) {
        List<DiceSide> diceSides0 = boardView.getDiceSide(id, 0);
        List<DiceSide> diceSides1 = boardView.getDiceSide(id, 1);
        int d = stratD.choseDice(diceSides0, diceSides1, null);
        DiceSide sideToRemove;
        DiceSide sideToAdd;
        do {
            sideToAdd = null;
            int resNum = 0;
            while (sideToAdd == null && resNum < resPriority.size()) {
                sideToAdd = stratF.execution(boardView.playableSides(id), resPriority.get(resNum));
                resNum++;
            }
            sideToRemove = stratD.choseSideRemove(boardView.getDiceSide(id, d), resPriority.get(resNum));
        } while (repeatInf && boardView.forge(id, d, sideToRemove, sideToAdd));
    }
}