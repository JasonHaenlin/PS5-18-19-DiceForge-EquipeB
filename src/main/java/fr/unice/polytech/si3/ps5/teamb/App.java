package fr.unice.polytech.si3.ps5.teamb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Raichu;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Totoro;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    private static void launchGame() throws Exception, InstantiationException, IllegalAccessException {
        Engine engine = new Engine();
        // @formatter:off
        String result = engine.createGame(1000)
                .addBot(Totoro.class)
                .addBot(Raichu.class)
                .fire();
        //@formatter:ons
        logger.info(result);
    }

    public static void main(String[] args) throws Exception {
        launchGame();
        launchGame();
    }

}
