package main.java.teamB.otake;

import java.util.ArrayList;
import java.util.Random;

class dice {

    ArrayList<face> face = new ArrayList();
    int size;
    int nombreAleatoire;




    dice(ArrayList<face> faces) {
        this.face = faces;
        this.size = face.size();
        Random r = new Random();
        this.nombreAleatoire = r.nextInt(size );
    }

    face random() {
        return face.get(nombreAleatoire);
    }

    int dicesize(){
        return size;
    }

}
