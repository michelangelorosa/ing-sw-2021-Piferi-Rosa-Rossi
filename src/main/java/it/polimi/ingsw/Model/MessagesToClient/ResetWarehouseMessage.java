package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;

/**
 * ResetWarehouseMessage Class contains data for a response message to be sent to the client
 * after a ResetWarehouse request.
 */
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

    /**
     * Setter for "warehouse" attribute in ResetWarehouseMessage Class.
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute in ResetWarehouseMessage Class.
     */
    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute in ResetWarehouseMessage Class.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute in ResetWarehouseMessage Class.
     */
    public ResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        for(Player player : game.getPlayers())
            if(player.getTurnPosition() == this.playerId) {
                player.setWarehouse(this.warehouse);
                player.setTemporaryResources(this.temporaryResources);
                player.setPossibleActions(this.possibleActions);
            }
    }
}
