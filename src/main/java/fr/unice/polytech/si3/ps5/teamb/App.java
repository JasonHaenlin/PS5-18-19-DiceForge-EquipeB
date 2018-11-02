package fr.unice.polytech.si3.ps5.teamb;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {

    private static final boolean MAKESTATISTICS = true;

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
