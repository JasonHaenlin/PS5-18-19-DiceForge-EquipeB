package fr.unice.polytech.si3.ps5.teamb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Raichu;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Totoro;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        Engine engine = new Engine();
        String result;

        // @formatter:off
        result = engine.createGame(1000)
                .addBot(Totoro.class)
                .addBot(Totoro.class)
                .addBot(Totoro.class)
                .addBot(Raichu.class)
                .fire();
                
        logger.info(result);

        result = engine.createGame(1000)
                .addBot(Raichu.class)
                .addBot(Raichu.class)
                .addBot(Raichu.class)
                .addBot(Raichu.class)
                .fire();

        logger.info(result);
        //@formatter:on
    }

}
