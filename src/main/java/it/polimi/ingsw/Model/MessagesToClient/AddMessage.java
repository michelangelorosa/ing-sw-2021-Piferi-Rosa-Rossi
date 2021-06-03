package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * AddMessage Class contains data for a response message to be sent to the client
 * after an AddResource action.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": the new Warehouse to be set to the corresponding player in the
 *     Client's View</li>
 *     <li>RedResourceStack "temporaryResources": contains resources which the player has
 *     yet to add to his Warehouse</li>
 * </ul>
 * @author redrick99
 */
public class AddMessage extends MessageToClient {
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
     * Setter for "warehouse" attribute.
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
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = this.getPlayer(userInteraction);

        if(success()) {
            player.setWarehouse(this.warehouse);
            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }

}
