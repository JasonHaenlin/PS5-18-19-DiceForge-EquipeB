package teamB.otake.diceForge;

/**
 * 
 */
public class DiceSide {
    protected int value;
    protected Resources type;

    DiceSide(int value, Resources type) {
        this.value = value;
        this.type = type;
    }

    /**
     * @return the type
     */
    public Resources getType() {
        return type;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
