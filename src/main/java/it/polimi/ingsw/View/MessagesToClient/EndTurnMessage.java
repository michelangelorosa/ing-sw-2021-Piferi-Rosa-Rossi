package it.polimi.ingsw.View.MessagesToClient;

public class EndTurnMessage extends MessageToClient {
    /** the next player's Id is needed to update the client's View */
    private int nextPlayerId;

    /**
     * Constructor for EndTurnMessage Class.
     */
    public EndTurnMessage(int playerId) {
        this.actionDone = ActionType.END_TURN;
        this.playerId = playerId;
    }
}
