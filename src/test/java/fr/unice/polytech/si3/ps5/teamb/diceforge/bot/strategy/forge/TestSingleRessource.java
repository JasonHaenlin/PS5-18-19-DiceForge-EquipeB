package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;


import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Resources;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.dice.DiceSide;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSingleRessource {

    private Config conf ;
    private SingleRessource forge;
    private List<DiceSide> diceSides;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        forge = new SingleRessource("BotTest ");
        diceSides = conf.getDice1Config(); //getDice1Config is public
    }

    @Test
    public void analyseDiceTest(){
        //assertEquals(Resources.GOLD, forge.analyseDice(diceSides));
    }

}
