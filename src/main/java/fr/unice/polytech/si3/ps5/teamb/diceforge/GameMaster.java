package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.List;

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
    private Bot winner;

    GameMaster() {
        this.winnerMsg = "";
    }

    /**
     * Find the winner and edit winner msg
     *
     * @param List bots
     *
     */

    void etablishWinner(List<Bot> bots) {
        bots.sort((Bot b1, Bot b2) -> Integer.compare(b1.getVictoryPoint(), b2.getVictoryPoint()));
        int i = bots.size() - 2;
        this.winner = bots.get(i + 1);
        while (winner.getVictoryPoint() == bots.get(i).getVictoryPoint() && i >= 0) {
            if (i == bots.size() - 2) {
                winnerMsg = "Egualite avec un total de " + winner.getVictoryPoint() +
                        " points de Gloire entre : " + winner.getName() + ", " + bots.get(i).getName();
            } else {
                winnerMsg += ", " + bots.get(i).getName();
            }
            i --;
        }if( winnerMsg == ""){
            winnerMsg = "Le bot " + winner.getName() + " gagne avec " + winner.getVictoryPoint()
                    + " points de Gloire \n GG!";
        }
    }

    /**
     * This method manage equality between 2 bots only (need revision)
     * 
     * @param bots
     * @return
     */
    private boolean isEquals(List<Bot> bots) {
        Bot botBefore = null;
        for (Bot bot : bots) {
            if (bot.equals(botBefore)) {
                return true;
            }
            botBefore = bot;
        }
        return false;
    }

    String getWinnerMsg() {
        return winnerMsg;
    }

    /**
     * @return the winner
     */
    public Bot getWinner() {
        return winner;
    }

}
