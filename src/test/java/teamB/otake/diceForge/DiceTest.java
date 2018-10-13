package teamB.otake.diceForge;

import org.junit.Test;

public class DiceTest{

    @Test
    public void diceTest(){
        try {
            Dice dice = new Dice();
        }catch (Exception e){
            System.out.println("Erreur constructeur");
        }
    }

}