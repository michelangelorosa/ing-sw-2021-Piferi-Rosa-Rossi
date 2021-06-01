package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ResetWarehouseMessage Class contains data for a response message to be sent to the client
 * after a ResetWarehouse request.
 */
public class ResetWarehouseMessage extends MessageToClient {
    private RedWarehouse warehouse;
    private RedResourceStack temporaryResources;

    /**
     * Constructor for ResetWarehouseMessage Class.
     */
    public ResetWarehouseMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.RESET_WAREHOUSE;
    }

    /**
     * Setter for "warehouse" attribute in ResetWarehouseMessage Class.
     */
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute in ResetWarehouseMessage Class.
     */
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute in ResetWarehouseMessage Class.
     */
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute in ResetWarehouseMessage Class.
     */
    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        Game game = userInteraction.getGame();
        for(Player player : game.getPlayers())
            if(player.getNickname().equals(this.getPlayerNickname())) {
                player.setWarehouse(this.warehouse);
                player.setTemporaryResources(this.temporaryResources);
                player.setPossibleActions(this.possibleActions);
            }
    }
}
