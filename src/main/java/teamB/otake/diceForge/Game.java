package teamB.otake.diceForge;

/**
 *Create a game with 2 bot
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */

public class Game {

    private final Bot bot1;
    private final Bot bot2;
    private String log;

    /**
     * Create a game
     */
    public Game(){
        bot1 = new Bot();
        bot2 = new Bot();
        log = "";

        bot1.play();
        log += "Le bot 1 lance les dés \n";
        log += "Le bot 1 a obtenue " + bot1.getVictoryPoint() + " points de victoire \n";

        bot2.play();
        log += "Le bot 2 lance les dés \n";
        log += "Le bot 2 a obtenu " + bot2.getVictoryPoint() + " points de victoire \n";
    }

    public String getLog(){
        return log;
    }

    /**
     * Print the log
     */
    public void printLog(){
        System.out.println(log);
    }
}
