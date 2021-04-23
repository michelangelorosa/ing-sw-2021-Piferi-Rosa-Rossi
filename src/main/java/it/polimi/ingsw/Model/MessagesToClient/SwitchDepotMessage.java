package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

/**
 * SwitchDepotMessage Class contains data for a response message to be sent to the client
 * after a SwitchDepot request.
 */
public class SwitchDepotMessage extends MessageToClient {
    /** A warehouse is needed to update the Client's View */
    private Warehouse warehouse;

    /**
     * Constructor for SwitchDepotMessage Class.
     */
    public SwitchDepotMessage(int playerId) {
        this.actionDone = ActionType.SWITCH_DEPOT;
        this.playerId = playerId;
    }

    /**
     * Setter for "warehouse" attribute in SwitchDepotMessage Class.
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Getter for "warehouse" attribute in SwitchDepotMessage Class.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }
}
