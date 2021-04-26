package it.polimi.ingsw.View.MessagesToClient;

public class BuyCardMessage extends MessageToClient {

    /**
     * Constructor for BuyCardMessage Class.
     */
    public BuyCardMessage(int playerId) {
        this.actionDone = ActionType.BUY_CARD;
        this.playerId = playerId;
    }
}
