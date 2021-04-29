package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;

/**
 * EndTurnMessage Class defines a response message to be sent to a client after
 * an EndTurn request.
 */
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

    /**
     * Setter for "nextPlayerId" attribute in EndTurnMessage Class.
     */
    public void setNextPlayerId(int nextPlayerId) {
        this.nextPlayerId = nextPlayerId;
    }

    /**
     * Getter for "nextPlayerId" attribute in EndTurnMessage Class.
     */
    public int getNextPlayerId() {
        return nextPlayerId;
    }
}