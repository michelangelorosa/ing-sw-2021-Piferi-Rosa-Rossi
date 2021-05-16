package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.ResourceStack;

/**
 * BuyCardMessage Class contains data for a response message to be sent to the client
 * after a BuyCard request.
 */
public class BuyCardMessage extends MessageToClient{
    it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources;

    /**
     * Constructor for BuyCardMessage Class.
     */
    public BuyCardMessage(int playerId) {
        this.actionDone = ActionType.BUY_CARD;
        this.playerId = playerId;
    }

    public it.polimi.ingsw.View.ReducedModel.ResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
