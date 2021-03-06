package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

/**
 * Define the different resources of the game
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */
public enum Resources {

    //@formatter:off
    VICTORY_POINT("Point de Gloire"), 
    GOLD("Or"), 
    MOON_STONE("Pierre Lune"), 
    SUN_STONE("Pierre Soleil");  
    //@formatter:on

    String resource;

    Resources(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return resource;
    }

}
