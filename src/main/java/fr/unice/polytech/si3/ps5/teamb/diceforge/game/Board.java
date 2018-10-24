package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.ArrayList;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.SimpleCard;

public class Board {

    private ArrayList<Card> MoonCards = new ArrayList<Card>();
    private ArrayList<Card> SunCards = new ArrayList<Card>();

    protected Board() {
        createCard();
    }

    protected void createCard() {

        for (int moon = 2; moon < 6; moon++) {
            MoonCards.add(new SimpleCard(moon, 0, 5 + moon));
        }
        for (int sun = 2; sun < 5; sun++) {
            SunCards.add(new SimpleCard(0, sun, 5 + sun));
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

    public Board getBoardView() {
        return this;
    }

}