package teamB.otake.diceForge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Dice {

    private List<Face> face = new ArrayList<>();
    private int size;
    private Random randDiceValue;

    public Dice(HashMap<String, Face> faces) {
        for (int i = 0; i < faces.size(); i++) {
            face.add(faces.get("f" + i));
        }
        this.size = face.size();
        randDiceValue = new Random();
    }

    public Face random() {
        return face.get(randDiceValue.nextInt(size));
    }

}
