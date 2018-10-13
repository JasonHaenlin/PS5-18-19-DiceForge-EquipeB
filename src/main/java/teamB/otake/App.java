package teamB.otake;

import java.util.HashMap;

import teamB.otake.diceForge.Bot;
import teamB.otake.diceForge.Dice;
import teamB.otake.diceForge.DiceSide;
import teamB.otake.diceForge.Resources;
import teamB.otake.diceForge.SimpleDiceSide;

public class App {

    public static void main(String[] args) {

        Bot bot1 = new Bot();
        Bot bot2 = new Bot();

        bot1.jouer();
        bot2.jouer();

        System.out.println("Bot 1 : " + bot1.getNombrePointDeGloire());
        System.out.println("Bot 2 : " + bot2.getNombrePointDeGloire());
    }
}
