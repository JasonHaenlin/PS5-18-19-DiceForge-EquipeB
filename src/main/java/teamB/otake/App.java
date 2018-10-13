package main.java.teamB.otake;


import java.util.HashMap;


public class App {

    public static void main(String[] args) {


        HashMap<String, DiceSide> mymap = new HashMap<>();

        for (int i = 0; i < 6; i++) {
            mymap.put("f" + i, new SimpleDiceSide(i + 1,Resources.PG));
        }

        Dice dice = new Dice(mymap);
        System.out.println("Gain : "+dice.random());
        System.out.println("Type : "+dice.diceFaceType());

    }
}
