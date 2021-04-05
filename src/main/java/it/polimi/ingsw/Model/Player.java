package it.polimi.ingsw.Model;

    /**
    *
    *
    */

public enum Player {
    PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR;

    /**
     *
     */

    private static final Player[] players = Player.values();
    public static Player getPlayer(int i) { return Player.players[i]; }
}
