package teamB.otake.diceForge;

/**
 * Create a game master
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */

class GameMaster {

    private String winnerMsg;

    private int scoreBot1;
    private int scoreBot2;

    GameMaster(){
        this.winnerMsg = "";
    }

    /**
     * Find the winner and edit winner msg
     *
     * @param bot1
     * @param bot2
     */

    void etablishWinner(Bot bot1, Bot bot2){
        scoreBot1 = bot1.getVictoryPoint();
        scoreBot2 = bot2.getVictoryPoint();

        if(scoreBot1 < scoreBot2){ winnerMsg = "Le bot 2 a gagné avec un score de : "+ scoreBot2;}
        else if(scoreBot2 < scoreBot1){winnerMsg = "Le bot 1 a gagné avec un score de : "+ scoreBot1;}
        else {winnerMsg = "Les deux bots sont à égalité avec un score de : " + scoreBot1;}
    }

    int getScoreBot1(){
        return scoreBot1;
    }

    int getScoreBot2(){
        return scoreBot2;
    }

    String getWinnerMsg(){
        return winnerMsg;
    }
}
