package main.java.teamB.otake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class dice {

    private ArrayList<face> face = new ArrayList();
    private int size;
    private int nombreAleatoire;

    dice(HashMap<String, face> faces) {
        for(int i =0;i < faces.size();i++) {
            face.add(faces.get("f" + i));
        }
        this.size = face.size();
        Random r = new Random();
        this.nombreAleatoire = r.nextInt(size );
    }

    face random() {
        return face.get(nombreAleatoire);
    }

}
