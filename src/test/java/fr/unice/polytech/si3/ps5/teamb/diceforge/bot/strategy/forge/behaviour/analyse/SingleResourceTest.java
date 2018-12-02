package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * Test class for SingleResource strategy block,
 * the dice config is the following
 * dice1 : 2VP 1MS 3G 3G 3G 1G
 * dice2 : 1SS 4G 4G 4G 4G 2G
 */
public class SingleResourceTest {

    private Config conf;

    StratDice strD;

    private List<DiceSide> diceSides1;
    private List<DiceSide> diceSides2;

    private List<DiceSide> diceSides3;

    private DiceSide diceSideAnswer;
    private DiceSide diceSideAnswer2;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/singleresource.json");
        strD = new SingleResource();
    }

    @Test
    public void choseSideRemoveTest() {
        diceSides1 = conf.getDice1Config();
        diceSides2 = conf.getDice2Config();

        diceSideAnswer = new DiceSide(1, Resources.GOLD);
        diceSideAnswer2 = new DiceSide(2, Resources.GOLD);

        assertEquals(diceSideAnswer, strD.chooseSideRemove(diceSides1, Resources.GOLD));
        assertEquals(diceSideAnswer2, strD.chooseSideRemove(diceSides2, Resources.GOLD));

        diceSides3 = new ArrayList<>();
        for (int i = 1; i < 7; i++)
            diceSides3.add(new DiceSide(1, Resources.VICTORY_POINT));

        assertEquals(null, strD.chooseSideRemove(diceSides3, Resources.GOLD));

    }

}
