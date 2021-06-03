package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ResetWarehouseMessage Class contains data for a response message to be sent to the client
 * after a ResetWarehouse request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": Warehouse to be set to the corresponding player int the Client's
 *     View</li>
 *     <li>RedResourceStack "temporaryResources": contains the number of resources which the player has
 *     to add to his Warehouse</li>
 * </ul>
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
     * Setter for "warehouse" attribute.
     */
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Setter for "temporaryResources" attribute.
     */
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Getter for "warehouse" attribute.
     */
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "temporaryResources" attribute.
     */
    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = this.getPlayer(userInteraction);

        if(this.success()) {
            player.setWarehouse(this.warehouse);
            player.setTemporaryResources(this.temporaryResources);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
