package fr.unice.polytech.si3.ps5.teamb.diceforge.helper;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.player.OnlyDice;
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

    private static PlayerTest playerTest;

    private PlayerTest() throws Exception {
        super(new Config(basic));
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