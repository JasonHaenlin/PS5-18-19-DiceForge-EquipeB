package main.java.teamB.otake;


import java.util.ArrayList;


public class App {

    public static void main(String[] args) {

        ArrayList<face> faces = new ArrayList<>();
        face face1 = new face(1);
        face face2 = new face(2);
        face face3 = new face(3);
        faces.add(face1);faces.add(face2);faces.add(face3);
        dice des = new dice(faces);
        System.out.println(des.random().getValue());



    }
}
