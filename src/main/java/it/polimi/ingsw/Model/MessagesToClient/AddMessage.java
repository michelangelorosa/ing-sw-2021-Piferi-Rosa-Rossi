package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

/**
 * AddMessage Class contains data for a response message to be sent to the client
 * after an AddResource action.
 */
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

    /**
     * Setter for "warehouse" attribute in AddMessage Class.
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute in AddMessage Class.
     */
    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute in AddMessage Class.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute in AddMessage Class.
     */
    public ResourceStack getTemporaryResources() {
        return temporaryResources;
    }
}
