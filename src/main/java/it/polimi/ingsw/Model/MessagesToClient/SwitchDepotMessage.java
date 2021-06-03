package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * SwitchDepotMessage Class contains data for a response message to be sent to the client
 * after a SwitchDepot request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": Warehouse to be set to the corresponding player int the Client's
 *     View</li>
 * </ul>
 */
public class SwitchDepotMessage extends MessageToClient {
    private RedWarehouse warehouse;

    /**
     * Constructor for SwitchDepotMessage Class.
     */
    public SwitchDepotMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.SWITCH_DEPOT;
    }

    /**
     * Setter for "warehouse" attribute.
     */
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Getter for "warehouse" attribute.
     */
    public RedWarehouse getWarehouse() {
        return warehouse;
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

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
