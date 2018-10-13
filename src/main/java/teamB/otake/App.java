package main.java.teamB.otake;


import java.util.HashMap;


public class App {

    public static void main(String[] args) {


        HashMap<String, face> mymap = new HashMap<>();

        for (int i = 0; i < 6; i++) {
            mymap.put("f" + i, new face(i + 1));
        }

        dice des = new dice(mymap);
        System.out.println(des.random().getValue());

    }
}
