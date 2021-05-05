package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.*;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.Controller.Actions.ActionType;

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

    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getTurnPosition() == this.playerId) {
                    player.setWarehouse(this.warehouse);
                    player.setPossibleActions(this.possibleActions);
                }
        else {
            //TODO error message
        }
    }
}
