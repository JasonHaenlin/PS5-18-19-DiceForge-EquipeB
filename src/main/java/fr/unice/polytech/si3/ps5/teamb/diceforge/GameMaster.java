package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.ArrayList;
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
    private ArrayList<Bot> winner;

    GameMaster() {
        this.winner = new ArrayList<Bot>();
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
        winner.add(bots.get(i+1));
        while (winner.get(0).getVictoryPoint() == bots.get(i).getVictoryPoint() && i >= 1) {
            if (i == bots.size() - 2) {
                winnerMsg = "Egualite avec un total de " + winner.get(0).getVictoryPoint() +
                        " points de Gloire entre : " + winner.get(0).getName() + ", " + bots.get(i).getName();
            } else {
                winnerMsg += ", " + bots.get(i).getName();
            }
            winner.add(bots.get(i));
            i --;
        }if( winnerMsg == ""){
            winnerMsg = "Le bot " + winner.get(0).getName() + " gagne avec " + winner.get(0).getVictoryPoint()
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
    public ArrayList<Bot> getWinner() {
        return winner;
    }

}
