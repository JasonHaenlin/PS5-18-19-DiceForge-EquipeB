package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.Dice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.forge.DiceSide;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si3.ps5.teamb.diceforge.game.exploit.Card;


/**
 * InventoryTest
 */
public class BoardTest {

    BoardExtends board;
    List<DiceSide> diceSides;   //Original Dice
    List<DiceSide> result;
    Board board2;

    @Before
    public void setup() {
        board = new BoardExtends();
    }

    @Before
    public void setup2(){
        diceSides = new ArrayList<>();
        result = new ArrayList<>();

        board2 = new Board();
        board.registrationToBoard("Cloud",1);
        board.registrationToBoard("Pika",1);
        board.initialize();
        for(int i = 0; i < 1000;i++){ //Cloud have a lot of gold
            Map<Resources, Integer> res = board.rolldice("Cloud");
            //BUG Cloud doesn't receive gold
        }
        for (int i = 1; i < 3; i++) {
            diceSides.add(new DiceSide(i, Resources.SUN_STONE));    //for the test, DiceSide is public
            diceSides.add(new DiceSide(i, Resources.MOON_STONE));
        }
        diceSides.add(new DiceSide(2, Resources.GOLD));
        diceSides.add(new DiceSide(1, Resources.VICTORY_POINT));

        for(int i = 1; i < 7;i++){result.add(new DiceSide(1,Resources.GOLD));}
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



    @Test
    public void testForge(){
        for(int i = 0; i <6;i++){
            board.forge("Cloud",0, diceSides.get(i),new DiceSide(1,Resources.GOLD),i);//Can t forge because he hasn't got gold
            board.forge("Pika",0, diceSides.get(i),new DiceSide(1,Resources.GOLD),i);
        }
        List<DiceSide> sidesCloud = board.getDice("Cloud",0).getDiceSides();
        List<DiceSide> sidesPika = board.getDice("Pika", 0).getDiceSides();

        for(int i = 0; i < 6;i++){
            System.out.println("Nombre de tour : " + i);
            assertEquals(result.get(i).getValue(), sidesCloud.get(i).getValue());
            assertEquals(result.get(i).getType(), sidesCloud.get(i).getType());

            assertEquals(diceSides.get(i).getValue(), sidesPika.get(i).getValue()); //Test OK
            assertEquals(diceSides.get(i).getType(), sidesPika.get(i).getType());   //Test OK
        }
    }


}