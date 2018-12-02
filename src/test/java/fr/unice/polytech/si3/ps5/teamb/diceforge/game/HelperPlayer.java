package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice2;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Tuple;

/**
 * PlayerTest
 */
public class HelperPlayer extends Board {

    static final String basic = ("src/test/resources/config/basic.json");

    public static final Player first = new OnlyDice();
    public static final Player second = new OnlyDice();
    public static final Player third = new OnlyDice();
    public static final Player fourth = new OnlyDice();
    public static final Player fifth = new OnlyDice2();

    public static final HelperPlayer inst = new HelperPlayer();

    private HelperPlayer() {
        super(new Config(basic));
        first.addBoard(new Tuple<String>("test", 0, 9), getBoard());
        registrationToBoard(first.toString());
        second.addBoard(new Tuple<String>("test", 0, 9), getBoard());
        registrationToBoard(second.toString());
        third.addBoard(new Tuple<String>("test", 0, 9), getBoard());
        registrationToBoard(third.toString());
        fourth.addBoard(new Tuple<String>("test", 0, 9), getBoard());
        registrationToBoard(fourth.toString());
        fifth.addBoard(new Tuple<String>("test", 0, 9), getBoard());
        registrationToBoard(fifth.toString());
        initialize();
    }

    public Board getBoard() {
        return getBoardView();
    }

}