package fr.unice.polytech.si3.ps5.teamb.diceforge.game;

import java.util.concurrent.atomic.AtomicInteger;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice2;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Player;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.Config;

/**
 * PlayerTest
 */
public class PlayerTest extends Board {

    static final String basic = ("src/test/resources/config/basic.json");

    public static final Player first = new OnlyDice();
    public static final Player second = new OnlyDice();
    public static final Player third = new OnlyDice();
    public static final Player fourth = new OnlyDice();
    public static final Player fifth = new OnlyDice2();

    private static PlayerTest playerTest;

    private PlayerTest() throws Exception {
        super(new Config(basic));
        first.addBoard(new AtomicInteger(0), getBoard());
        second.addBoard(new AtomicInteger(0), getBoard());
        third.addBoard(new AtomicInteger(0), getBoard());
        fourth.addBoard(new AtomicInteger(0), getBoard());
        fifth.addBoard(new AtomicInteger(0), getBoard());
    }

    public Board getBoard() {
        return getBoardView();
    }

    public static PlayerTest getInstance() throws Exception {
        if (playerTest == null) {
            playerTest = new PlayerTest();
        }
        return playerTest;
    }

}