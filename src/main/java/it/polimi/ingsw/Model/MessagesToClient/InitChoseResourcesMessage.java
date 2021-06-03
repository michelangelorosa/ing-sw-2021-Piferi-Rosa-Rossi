package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * InitChoseResources Class defines a response message to be sent to a client after
 * an InitChooseResources Action request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": Warehouse to be set to the corresponding player inside
 *     the reduced game</li>
 * </ul>
 * @author redrick99
 */
public class InitChoseResourcesMessage extends MessageToClient {
    private RedWarehouse warehouse;

    /**
     * Constructor for InitChoseResourcesMessage.
     */
    public InitChoseResourcesMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.INIT_CHOOSE_RESOURCES;
    }

    /**
     * Getter for "warehouse" attribute.
     */
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Setter for "warehouse" attribute.
     */
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = getPlayer(userInteraction);

        if(success()) {
            player.setWarehouse(this.warehouse);
            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
