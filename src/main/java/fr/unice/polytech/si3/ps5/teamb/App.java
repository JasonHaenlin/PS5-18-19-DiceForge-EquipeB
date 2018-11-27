package fr.unice.polytech.si3.ps5.teamb;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        Engine engine = new Engine();
        // @formatter:off
        String result = engine.createGame(1000)
                .addBot(Pika.class)
                .addBot(Cloud.class)
                .addBot(Hephaestos.class)
                .addBot(Totoro.class)
                .addBot(Rem.class)
                .fire();
        //@formatter:ons

        logger.info(result);

    }
}
