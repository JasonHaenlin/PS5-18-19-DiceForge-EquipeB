package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class SingleResourceTest {

    private Config conf;

    StratDice strD;

    private List<DiceSide> diceSides1;
    private List<DiceSide> diceSides2;

    private List<DiceSide> diceSides3;

    private DiceSide diceSideAnswer;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        strD = new SingleResource();
        conf.getDice1Config(); // getDice1Config is public
    }

    @Test
    public void choseSideRemoveTest() {
        diceSides1 = conf.getDice1Config();
        diceSides2 = conf.getDice2Config();

        diceSideAnswer = new DiceSide(1, Resources.GOLD);

        assertEquals(diceSideAnswer, strD.choseSideRemove(diceSides1, Resources.GOLD));
        assertEquals(diceSideAnswer, strD.choseSideRemove(diceSides2, Resources.GOLD));

        diceSides3 = new ArrayList<>();
        for (int i = 1; i < 7; i++)
            diceSides3.add(new DiceSide(1, Resources.VICTORY_POINT));

        assertEquals(null, strD.choseSideRemove(diceSides3, Resources.GOLD));
    }

}
