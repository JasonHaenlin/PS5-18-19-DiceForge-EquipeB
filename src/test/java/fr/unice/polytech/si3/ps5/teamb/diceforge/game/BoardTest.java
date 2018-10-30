package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;


/**
 * InventoryTest
 */
public class BoardTest {

    BoardExtends board;

    @Before
    public void setup() {
        board = new BoardExtends();
    }

    @Test
    public void gameBoardTest() {
        board.BoardTest();

    }

    @Test
    public void getEligibleCardsTest(){
        board.initialize(); //on ne peut pas créer de faces autrement pour des raisons de sécurité
        List<Card> eligibleCardsList = board.getEligibleCards(5,5);
        assertEquals(7, eligibleCardsList.size());

        List<Card> eligibleMoonCardsList = board.getEligibleCards(5,0);
        assertEquals(4, eligibleMoonCardsList.size());

        List<Card> eligibleSunCardsList = board.getEligibleCards(0,5);
        assertEquals(3, eligibleSunCardsList.size());

        List<Card> emptyEligibleCardsList = board.getEligibleCards(1,1);
        assertEquals(0, emptyEligibleCardsList.size());



        //TODO print à changer quand l'initialisation sera plus précise
        for (Card c : eligibleCardsList){
            System.out.println("Coût en pierre de lune : " + c.getMoonStone());
            System.out.println("Coût en pierre de soleil : " + c.getSunStone());
            System.out.println("Nombre de pts de victoire : " + c.getVictoryPoint());
        }
    }


}