package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ParseHelper
 */
public class ParseHelper {

    private ParseHelper() {

    }

    public static final Map<String, String> parseResult(String bot1, String bot2, String result) {
        Map<String, String> winner = new HashMap<>();
        String[] str = result.split("\n");
        winner.put(bot1, getScore(str, bot1));
        winner.put(bot2, getScore(str, bot2));
        return winner;
    }

    public static final String getScore(String[] str, String name) {
        for (int i = 0; i < str.length; i++) {
            if (str[i].contains(name)) {
                return str[i];
            }
        }
        return "";
    }
}