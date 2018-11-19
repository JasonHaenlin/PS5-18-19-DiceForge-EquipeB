package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Guard
 */
public class Guard {

    private String playerToken;
    private String LastPlayer;
    private int authNb;
    private int authCt;

    private Map<String, Integer> tokenMap;

    public Guard(int authNb) {
        setup(authNb);
    }

    public Guard() {
        setup(1);
    }

    private void setup(int authNb) {
        this.authCt = 1;
        this.authNb = authNb;
        this.playerToken = "";
        this.LastPlayer = "";
        this.tokenMap = new HashMap<>();
    }

    public boolean add(String name, int playerToken) {
        if (tokenMap.containsKey(name)) {
            return false;
        }
        tokenMap.put(name, playerToken);
        return true;
    }

    public boolean enableAuthorization(String player) {
        authCt = 1;
        playerToken = player;
        LastPlayer = player;
        return playerToken != null;
    }

    public void removeAuthorization() {
        playerToken = "";
    }

    public boolean isAuthorizated(String player) {
        return playerToken.equals(player);
    }

    public String peekLastPlayer() {
        return LastPlayer;
    }

    public boolean repeatAuth() {
        if (!playerToken.equals("")) {
            return false;
        }
        if (authCt < authNb) {
            playerToken = LastPlayer;
            authCt++;
            return true;
        }
        return false;
    }

}