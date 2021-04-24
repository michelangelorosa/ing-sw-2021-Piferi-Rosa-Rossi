package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.Warehouse;



public class AddMessage extends MessageToClient {
    /** warehouse and temporaryResources attributes are needed to update the client's View */
    private Warehouse warehouse;
    private ResourceStack temporaryResources;

    /**
     * Constructor for AddMessage Class.
     * @param playerId
     */
    public AddMessage(int playerId) {
        this.actionDone = ActionType.ADD_RESOURCE;
        this.playerId = playerId;
    }
}
