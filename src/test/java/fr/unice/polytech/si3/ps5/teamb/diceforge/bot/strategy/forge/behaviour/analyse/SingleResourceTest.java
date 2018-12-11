package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge.behaviour.analyse;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.side.SideSimple;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * Test class for SingleResource strategy block, the dice config is the
 * following dice1 : 2VP 1MS 3G 3G 3G 1G dice2 : 1SS 4G 4G 4G 4G 2G
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
        strD = new StratDiceSingleResource();
    }

    @Test
    public void choseSideRemoveTest() {
        diceSides1 = conf.getDice1Config();
        diceSides2 = conf.getDice2Config();

        diceSideAnswer = new SideSimple(Resources.GOLD, 1, 0);
        diceSideAnswer2 = new SideSimple(Resources.GOLD, 2, 0);

        assertEquals(diceSideAnswer, strD.chooseSideRemove(diceSides1, Resources.GOLD));
        assertEquals(diceSideAnswer2, strD.chooseSideRemove(diceSides2, Resources.GOLD));

        diceSides3 = new ArrayList<>();
        for (int i = 1; i < 7; i++)
            diceSides3.add(new SideSimple(Resources.VICTORY_POINT, 1, 0));

        assertEquals(null, strD.chooseSideRemove(diceSides3, Resources.GOLD));

    }

}
