package fr.unice.polytech.si3.ps5.teamb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Pika;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Rem;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;

public class App {
    private static Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        Game game = new Game();

        // @formatter:off
        String result = game.setup(2)
            .addBot(Pika.class)
            .addBot(Rem.class)
            .fire();
        //@formatter:on

        LOGGER.trace(result);
    }
}
