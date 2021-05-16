package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

/**
 * AddMessage Class contains data for a response message to be sent to the client
 * after an AddResource action.
 */
public class AddMessage extends MessageToClient {
    /** warehouse and temporaryResources attributes are needed to update the client's View */
    private it.polimi.ingsw.View.ReducedModel.Warehouse warehouse;
    private it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources;

    /**
     * Constructor for AddMessage Class.
     */
    public AddMessage(int playerId) {
        this.actionDone = ActionType.ADD_RESOURCE;
        this.playerId = playerId;
    }

    /**
     * Setter for "warehouse" attribute in AddMessage Class.
     */
    public void setWarehouse(it.polimi.ingsw.View.ReducedModel.Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute in AddMessage Class.
     */
    public void setTemporaryResources(it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute in AddMessage Class.
     */
    public it.polimi.ingsw.View.ReducedModel.Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute in AddMessage Class.
     */
    public it.polimi.ingsw.View.ReducedModel.ResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /*

     TODO import view methods
    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    /*@Override
    public void updateView(it.polimi.ingsw.View.ReducedModel.Game game) {
        if(this.error.equals("SUCCESS"))
            for(it.polimi.ingsw.View.ReducedModel.Player player : game.getPlayers())
                if(player.getTurnPosition() == this.playerId) {
                    player.setWarehouse(this.warehouse);
                    player.setTemporaryResources(this.temporaryResources);
                    player.setPossibleActions(this.possibleActions);
                }
                else {
                    //TODO error message
                }
    } */

}
