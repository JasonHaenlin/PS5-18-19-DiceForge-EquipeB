package fr.unice.polytech.si3.ps5.teamb.diceforge;

public abstract class Card {

    int moonStone;
    int sunStone;
    int victoryPoint;

    Card(int moonStone, int sunStone, int victoryPoint) {
        this.moonStone = moonStone;
        this.sunStone = sunStone;
        this.victoryPoint = victoryPoint;

    }

    public int getVictoryPoint() {
        return victoryPoint;
    }

    public int getMoonStone() {
        return moonStone;
    }

    public int getSunStone() {
        return sunStone;
    }

}