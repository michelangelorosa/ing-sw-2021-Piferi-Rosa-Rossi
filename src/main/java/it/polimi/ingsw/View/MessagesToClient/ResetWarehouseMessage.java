package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.Warehouse;

public class ResetWarehouseMessage extends MessageToClient {
    private Warehouse warehouse;
    private ResourceStack temporaryResources;

    /**
     * Constructor for ResetWarehouseMessage Class.
     */
    public ResetWarehouseMessage(int playerId) {
        this.actionDone = ActionType.RESET_WAREHOUSE;
        this.playerId = playerId;
    }
}
