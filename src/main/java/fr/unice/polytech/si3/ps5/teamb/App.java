package fr.unice.polytech.si3.ps5.teamb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Pika;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.Rem;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Game;

import java.util.Map;

public class App {

    private static final boolean MAKESTATISTICS = true;
    private static final int NUMBEROFGAMES = 1000;

    private static Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {



        //TODO mettre ce code dans une classe engine
        if (!MAKESTATISTICS){
            Game game = new Game();

            // @formatter:off
            String result = game.setup(2)
                    .addBot(Pika.class)
                    .addBot(Rem.class)
                    .oneGameFire();
            //@formatter:on

            LOGGER.trace(result);
        } else {
            int winCountPika = 0;
            int winCountRem = 0;

            //TODO fix the player factor
            int drawCount = 0; //is multiplied by the number of players

            int winningTotalScorePika = 0;
            int winningTotalScoreRem = 0;
            int drawTotalScore = 0;
            String result;
            for (int i = 0; i < NUMBEROFGAMES; i++) {
                Game game = new Game();
                game.setup(2);
                game.addBot(Pika.class);
                game.addBot(Rem.class);
                Map<String, Integer> interResult = game.statsModeFire();
                for (Map.Entry<String, Integer> entry : interResult.entrySet()) {
                    if (interResult.size() == 1) {
                        if (entry.getKey() == "Pikachu"){
                            winCountPika++;
                            winningTotalScorePika += entry.getValue();
                        }
                        else if (entry.getKey() == "Rem"){
                            winCountRem++;
                            winningTotalScoreRem += entry.getValue();
                        }
                    } else {
                        drawCount++;
                        drawTotalScore += entry.getValue();
                    }
                }

            }

            System.out.println("AI Pika's winrate is " + winCountPika/10 + "% with a mean score of" + winningTotalScorePika/winCountPika +
                    "\nAI Rem's winrate is " + winCountRem/10 + "% with a mean score of " + winningTotalScoreRem/winCountRem);
            if (drawCount != 0) {
                System.out.println("Draw rate is " + drawCount/20 + "% with a mean score of " + drawTotalScore/drawCount);
            } else {
                System.out.println("No draw game");
            }
        }

    }
}
