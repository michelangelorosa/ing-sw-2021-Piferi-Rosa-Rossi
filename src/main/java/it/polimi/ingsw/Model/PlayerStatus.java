package it.polimi.ingsw.Model;

/**
 * PlayerStatus enum-type Class contains all possible statuses a player could have
 * while playing the game.
 */

public enum PlayerStatus {
    IN_GAME, IDLE, LOST, WON;

    /**
     * This particular method uses an array of PlayerStatus instances containing each different marble
     * to return a particular playerStatus based on the integer given when calling the method.
     */
    private static final PlayerStatus[] playerStatuses = PlayerStatus.values();
    public static PlayerStatus getPlayerStatus(int i) { return PlayerStatus.playerStatuses[i]; }
}
