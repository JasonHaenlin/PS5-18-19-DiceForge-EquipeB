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
    private final GameMaster gameMaster;
    private String log;

    /**
     * Create a game
     */
    public Game(){
        bot1 = new Bot();
        bot2 = new Bot();
        gameMaster = new GameMaster();
        log = "";

        bot1.play();
        log += "Le bot 1 lance les dés \n";
        log += "Le bot 1 a obtenue " + bot1.getVictoryPoint() + " points de victoire \n";

        bot2.play();
        log += "Le bot 2 lance les dés \n";
        log += "Le bot 2 a obtenu " + bot2.getVictoryPoint() + " points de victoire \n";

        //The log print the name of winner
        gameMaster.etablishWinner(bot1,bot2);
        log += gameMaster.getWinnerMsg();
    }

    //Use for test
    String getLog(){
        return log;
    }

    /**
     * Print the log
     */
    public void printLog(){
        System.out.println(log);
    }
}
