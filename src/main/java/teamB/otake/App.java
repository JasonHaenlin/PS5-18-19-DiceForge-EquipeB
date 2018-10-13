package teamB.otake;

import teamB.otake.diceForge.Bot;

public class App {

    public static void main(String[] args) {

        Bot bot1 = new Bot();
        Bot bot2 = new Bot();

        bot1.play();
        bot2.play();

        System.out.println("Bot 1 : " + bot1.getVictoryPoint());
        System.out.println("Bot 2 : " + bot2.getVictoryPoint());
    }
}
