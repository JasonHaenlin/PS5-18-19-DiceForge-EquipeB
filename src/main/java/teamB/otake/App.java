package teamB.otake;

import java.util.HashMap;

import teamB.otake.diceForge.Dice;
import teamB.otake.diceForge.Face;

public class App {

    public static void main(String[] args) {

        HashMap<String, Face> mymap = new HashMap<>();

        for (int i = 0; i < 6; i++) {
            mymap.put("f" + i, new Face(i + 1));
        }

        Dice des = new Dice(mymap);
        System.out.println(des.random().getValue());

    }
}
