package teamb.otake.diceforge;

/**
 *
 * @author Ruben Houri
 * @author Maxime Castellano
 * @author Vincent Ung
 * @author Jason Haenlin
 */
class DiceSide {
    protected int value;
    protected Resources type;

    DiceSide(int value, Resources type) {
        this.value = value;
        this.type = type;
    }

    /**
     * @return the type
     */
    Resources getType() {
        return type;
    }

    /**
     * @return the value
     */
    int getValue() {
        return value;
    }
}
