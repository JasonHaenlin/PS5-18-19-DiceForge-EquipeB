package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.forge;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

public class TestSingleRessource {

    private Config conf;

    @Before
    public void setup() throws Exception {
        conf = new Config("src/test/resources/config/basic.json");
        new SingleRessource("BotTest ");
        conf.getDice1Config(); // getDice1Config is public
    }

    @Test
    public void analyseDiceTest() {
        // assertEquals(Resources.GOLD, forge.analyseDice(diceSides));
    }

}
