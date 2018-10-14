package teamb.otake;

import teamb.otake.diceforge.Game;

public class App {

    public static void main(String[] args) {

        Game game = new Game();

        // @formatter:off
        String result = game.setUp(2)
            .addBot("Rondoudou")
            .addBot("Pichu")
            .addBot("Rem")
            .addBot("Ram")
            .fire();
        //@formatter:on

        System.out.println(result);
    }
}
