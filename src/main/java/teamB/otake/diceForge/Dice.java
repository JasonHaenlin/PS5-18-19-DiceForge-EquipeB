package teamB.otake.diceForge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Dice {

    private List<DiceSide> diceSides = new ArrayList<>();

    private int size;
    Random rnd;

    public Dice(HashMap<String, DiceSide> Side) {
        rnd = new Random();
        for (int i = 0; i < Side.size(); i++) {
            diceSides.add(Side.get("f" + i));
        }
        this.size = diceSides.size();
    }

    public int random() {
        return diceSides.get(rnd.nextInt(size)).getValue();
    }

    public String diceFaceType() {
        return diceSides.get(rnd.nextInt(size)).getType().toString();
    }

}
