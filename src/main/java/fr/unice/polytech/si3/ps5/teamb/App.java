package fr.unice.polytech.si3.ps5.teamb;

import fr.unice.polytech.si3.ps5.teamb.diceforge.Game;

public class App {

    public static void main(String[] args) {

        Game game = new Game();

        // @formatter:off
        String result = game.setUp(1)
            .addBot("Rondoudou")
            .addBot("Pichu")
            .addBot("Rem")
            .addBot("Ram")
            .fire();
        //@formatter:on

        System.out.println(result);
    }
}
