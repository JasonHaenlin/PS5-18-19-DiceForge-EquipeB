package fr.unice.polytech.si3.ps5.teamb;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Pika;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Rem;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;

import java.util.Map;

public class App {

    private static final boolean MAKESTATISTICS = false;

    private static Logger LOGGER = LogManager.getLogger(App.class);
    private static String result;


    public static void main(String[] args) throws Exception {



        Engine engine = new Engine(true);
        if (!MAKESTATISTICS){
            engine.launchGame();
        } else {
            engine.statsModeLaunchGame();
        }
        result = engine.getResult();
        LOGGER.trace(result);

    }
}
