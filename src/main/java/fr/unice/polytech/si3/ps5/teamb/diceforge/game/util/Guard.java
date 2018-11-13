package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Guard
 */
public class Guard {

    private Integer token;
    private Integer last_token;
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
        this.token = 0;
        this.last_token = 0;
        this.tokenMap = new HashMap<>();
    }

    public boolean add(String name, int token) {
        if (tokenMap.containsKey(name)) {
            return false;
        }
        tokenMap.put(name, token);
        return true;
    }

    public boolean enableAuthorization(String player) {
        authCt = 1;
        token = tokenMap.get(player);
        last_token = tokenMap.get(player);
        return token != null;
    }

    public void removeAuthorization() {
        token = 0;
    }

    public boolean isAuthorizated(String player) {
        return tokenMap.get(player).equals(token);
    }

    public boolean repeatAuth() {
        if (authCt < authNb) {
            token = last_token;
            authCt++;
            return true;
        }
        return false;
    }

}