package teamB.otake;

import teamB.otake.diceForge.Game;

public class App {

    public static void main(String[] args) {

        Game game = new Game();

        // @formatter:off
        String result = game.setUp(1)
            .addBot("Rondoudou")
            .addBot("Pichu")
            .fire();
        //@formatter:on

        System.out.println(result);
    }
}
