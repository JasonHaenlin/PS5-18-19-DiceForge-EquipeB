package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.Bot;

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
        winner.add(bots.get(i + 1));
        while (winner.get(0).getVictoryPoint() == bots.get(i).getVictoryPoint() && i >= 1) {
            winner.add(bots.get(i));
            i--;
        }
        if(winner.size() == 1){
            winnerMsg = "Le bot " + winner.get(0).getName() + " gagne avec " + winner.get(0).getVictoryPoint()
                    + " points de Gloire";
        }
        else{
            winnerMsg = "Egualite avec un total de " + winner.get(0).getVictoryPoint()
                    + " points de Gloire entre : ";
            for(int j = 0; j < winner.size(); j++){
                winnerMsg += winner.get(j).getName() + ", ";
            }
        }
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
