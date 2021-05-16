package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

/**
 * ResetWarehouseMessage Class contains data for a response message to be sent to the client
 * after a ResetWarehouse request.
 */
public class ResetWarehouseMessage extends MessageToClient {
    private it.polimi.ingsw.View.ReducedModel.Warehouse warehouse;
    private it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources;

    /**
     * Constructor for ResetWarehouseMessage Class.
     */
    public ResetWarehouseMessage(int playerId) {
        this.actionDone = ActionType.RESET_WAREHOUSE;
        this.playerId = playerId;
    }

    /**
     * Setter for "warehouse" attribute in ResetWarehouseMessage Class.
     */
    public void setWarehouse(it.polimi.ingsw.View.ReducedModel.Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute in ResetWarehouseMessage Class.
     */
    public void setTemporaryResources(it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute in ResetWarehouseMessage Class.
     */
    public it.polimi.ingsw.View.ReducedModel.Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute in ResetWarehouseMessage Class.
     */
    public it.polimi.ingsw.View.ReducedModel.ResourceStack getTemporaryResources() {
        return temporaryResources;
    }
}
