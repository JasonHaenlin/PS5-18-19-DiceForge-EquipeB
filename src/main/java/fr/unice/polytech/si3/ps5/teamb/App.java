package fr.unice.polytech.si3.ps5.teamb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Cloud;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Hephaestos;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Pika;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Totoro;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        Engine engine = new Engine();
        // @formatter:off
        String result = engine.createGame(1000)
                .addBot(Hephaestos.class)
                .addBot(Cloud.class)
                .addBot(Pika.class)
                .addBot(Totoro.class)
                .fire();
        //@formatter:ons

        logger.info(result);

    }
}
