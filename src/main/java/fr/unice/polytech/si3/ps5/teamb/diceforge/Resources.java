package fr.unice.polytech.si3.ps5.teamb.diceforge;

/**
 * Define different of resource
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */
enum Resources {

    PG("Point de Gloire"), G("Or"), MS("Pierre Lune"), SS("Pierre Soleil");

    String resource;

    Resources(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return resource;
    }

}
