package fr.unice.polytech.si3.ps5.teamb;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Cloud;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Pika;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Rem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        Engine engine = new Engine();
        // @formatter:off
        String result = engine.createGame(50)
                .addBot(Pika.class)
                .addBot(Rem.class)
                .addBot(Rem.class)
                .fire();
        //@formatter:on

        log.info(result);

    }
}
