package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;

/**
 * AddMessage Class contains data for a response message to be sent to the client
 * after an AddResource action.
 */
public class AddMessage extends MessageToClient {
    /** warehouse and temporaryResources attributes are needed to update the client's View */
    private RedWarehouse warehouse;
    private RedResourceStack temporaryResources;

    /**
     * Constructor for AddMessage Class.
     */
    public AddMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ADD_RESOURCE;
    }

    /**
     * Setter for "warehouse" attribute in AddMessage Class.
     */
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute in AddMessage Class.
     */
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute in AddMessage Class.
     */
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute in AddMessage Class.
     */
    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }


    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getNickname().equals(this.getPlayerNickname())) {
                    player.setWarehouse(this.warehouse);
                    player.setTemporaryResources(this.temporaryResources);
                    player.setPossibleActions(this.possibleActions);
                }
                else {
                    //TODO error message
                }
    }

}
