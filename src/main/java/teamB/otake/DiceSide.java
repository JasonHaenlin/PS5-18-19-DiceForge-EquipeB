package main.java.teamB.otake;

abstract class DiceSide {
    protected int valeur;
    protected Resources type;

    int getValue() {
        return valeur;
    }

    String getType(){
        return type.resource;
    }
}
