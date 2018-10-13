package main.java.teamB.otake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Dice {

    private ArrayList<DiceSide> DiceSide = new ArrayList();
    private int size;
    private int nombreAleatoire;

    Dice(HashMap<String, DiceSide> Side) {
        for(int i =0;i < Side.size();i++) {
           DiceSide.add(Side.get("f" + i));
        }
        this.size = DiceSide.size();
        Random r = new Random();
        this.nombreAleatoire = r.nextInt(size );
    }

    int random() {
        return DiceSide.get(nombreAleatoire).getValue();
    }
    String diceFaceType() {
        return DiceSide.get(nombreAleatoire).getType();
    }

}
