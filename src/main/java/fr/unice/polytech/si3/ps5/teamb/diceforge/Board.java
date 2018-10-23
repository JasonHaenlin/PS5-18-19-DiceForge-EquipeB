package fr.unice.polytech.si3.ps5.teamb.diceforge;

import java.util.ArrayList;

class Board {

    public ArrayList<Card> MoonCards = new ArrayList<Card>();
    public ArrayList<Card> SunCards = new ArrayList<Card>();

    public void createCard() {

        for (int moon = 1; moon < 4; moon++) {
            MoonCards.add(new SimpleCard(moon, 0, 10 + moon));
        }
        for (int sun = 1; sun < 4; sun++) {
            SunCards.add(new SimpleCard(0, sun, 10 + sun));
        }

    }

    public ArrayList<Card> getEligibleCards(int MoonBank, int SunBank) {
        ArrayList<Card> Buyable = new ArrayList<Card>();
        for (Card Card : MoonCards) {
            if (Card.getMoonStone() <= MoonBank) {
                Buyable.add(Card);
            }
        }
        for (Card Card : SunCards) {
            if (Card.getSunStone() <= SunBank) {
                Buyable.add(Card);
            }
        }
        Buyable.sort((Card a1, Card a2) -> Integer.compare(a1.getMoonStone() + a1.getSunStone(),
                a2.getMoonStone() + a2.getSunStone()));

        return Buyable;
    }

}