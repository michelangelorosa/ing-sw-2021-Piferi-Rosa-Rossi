package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;

import java.util.ArrayList;

/**
 * EndMarketMessage Class defines a response message to be sent to the client after
 * a EndMarket request.
 */
public class EndMarketMessage extends MessageToClient {
    /** All players position onto the faith track is needed to update the clients' View */
    private ArrayList<Integer> playersFaithPosition;

    /**
     * Constructor for EndMarketMessage Class.
     */
    public EndMarketMessage(int playerId) {
        this.actionDone = ActionType.END_MARKET;
        this.playerId = playerId;
    }

    /**
     * Setter for "playersFaithPosition" attribute in EndMarketMessageClass.
     */
    public void setPlayersFaithPosition(ArrayList<Integer> playersFaithPosition) {
        this.playersFaithPosition = playersFaithPosition;
    }

    /**
     * Getter for "playersFaithPosition" attribute in EndMarketMessageClass.
     */
    public ArrayList<Integer> getPlayersFaithPosition() {
        return playersFaithPosition;
    }
}
