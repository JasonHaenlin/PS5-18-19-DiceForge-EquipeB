package teamB.otake.diceForge;

/**
 * Create a bot
 *
 * A bot play the game
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 *
 */

public class Bot {

    private int nombrePointDeGloire;
    private Dice dice;

    /**
     * constructor
     */
    public Bot(){
        dice = new Dice();
        nombrePointDeGloire = 0;
    }

    public void jouer(){
        nombrePointDeGloire += lancer();
    }

    /**
     *
     * @return the number of dice
     */

    private int lancer(){
        return dice.random();
    }

    public int getNombrePointDeGloire(){
        return nombrePointDeGloire;
    }
}
