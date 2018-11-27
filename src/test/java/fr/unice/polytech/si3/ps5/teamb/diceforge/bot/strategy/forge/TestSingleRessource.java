package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import static org.junit.Assert.assertEquals;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

import java.util.ArrayList;
import java.util.List;

public class TestSingleRessource {

    private Config conf;

    Forge forge;

    private List<DiceSide> diceSides1;
    private List<DiceSide> diceSides2;

    private List<DiceSide> feasible1;
    private List<DiceSide> feasible2;

    private DiceSide diceSideAnswer;


    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        forge = new SingleRessource("BotTest ");
        conf.getDice1Config(); // getDice1Config is public
    }

    @Test
    public void choseSideRemoveTest() {
        diceSides1 = conf.getDice1Config();
        diceSides2 = conf.getDice2Config();

        diceSideAnswer = new DiceSide(1, Resources.GOLD);

        assertEquals(diceSideAnswer, forge.choseSideRemove(diceSides1, Resources.GOLD));
        assertEquals(diceSideAnswer, forge.choseSideRemove(diceSides2, Resources.GOLD));
    }

    @Test
    public void computeTest(){
        feasible1 = new ArrayList<>();
        feasible1.add(new DiceSide(1, Resources.VICTORY_POINT));
        feasible1.add(new DiceSide(1, Resources.GOLD));
        feasible1.add(new DiceSide(1, Resources.MOON_STONE));

        assertEquals(new DiceSide(1, Resources.GOLD), forge.compute(feasible1,Resources.GOLD));

        feasible2 = new ArrayList<>();
        feasible2.add(new DiceSide(1, Resources.VICTORY_POINT));
        feasible2.add(new DiceSide(1, Resources.GOLD));
        feasible2.add(new DiceSide(3, Resources.GOLD));

        assertEquals(new DiceSide(3, Resources.GOLD), forge.compute(feasible2,Resources.GOLD));
    }

    @Test
    public void analyseDiceTest(){
        diceSides1 = conf.getDice1Config();
        diceSides2 = conf.getDice2Config();

        assertEquals(4, forge.analyseDice(diceSides1, Resources.GOLD));
        assertEquals(1, forge.analyseDice(diceSides2, Resources.SUN_STONE));
    }

}
