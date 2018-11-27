package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Guard
 */
public class Guard {

    private String playerToken;
    private int nbOfSlot;

    private List<Boolean> slotAuth;

    public Guard(int slotAuth) {
        setup(slotAuth);
    }

    public Guard() {
        setup(1);
    }

    private void setup(int nbOfSlot) {
        this.nbOfSlot = nbOfSlot;
        this.playerToken = "";
        this.slotAuth = new ArrayList<>();
        for (int i = 0; i < nbOfSlot; i++) {
            slotAuth.add(false);
        }
    }

    public boolean enableAuthorization(String player) {
        playerToken = player;
        switchAuth(true);
        return playerToken != null;
    }

    public void revokeAuthorization() {
        switchAuth(false);
    }

    public void revokeAuthorizationPartially(String player, int slot) {
        if (playerToken.equals(player))
            slotAuth.set(slot - 1, false);
    }

    public boolean isAuthorizated(String player, int slot) {
        return slotAuth.get(slot - 1) && playerToken.equals(player);

    }

    public String peekLastPlayer() {
        return playerToken;
    }

    private void switchAuth(boolean state) {
        for (int i = 0; i < nbOfSlot; i++) {
            slotAuth.set(i, state);
        }
    }

}