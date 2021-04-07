package it.polimi.ingsw.Model;

/**
 * Four Leader Card actions, byte based
 */
public enum LeaderCardAction {
    DISCOUNT,WHITEMARBLE,PRODUCTIONPOWER,EXTRADEPOT;

    private static final LeaderCardAction[] action = LeaderCardAction.values();
    public static LeaderCardAction getLeaderCardAction(byte b) { return LeaderCardAction.action[b]; }
}
