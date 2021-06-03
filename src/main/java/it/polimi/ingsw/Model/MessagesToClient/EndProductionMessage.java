package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * EndProductionMessage Class defines methods and attributes to update the Client's View after
 * a PayResourceProduction Action.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": the new Warehouse to be set to the corresponding player in the
 *     Client's View</li>
 *     <li>RedStrongbox "strongbox": the new Strongbox to be set to the corresponding player in the
 *     Client's View</li>
 * </ul>
 * @author redrick99
 */
public class EndProductionMessage extends MessageToClient {
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;

    /**Constructor for EndProductionMessage Class.*/
    public EndProductionMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_PAY_PRODUCTION;
    }

    /**Getter for "warehouse" attribute.*/
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    /**Getter for "strongbox" attribute.*/
    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    /**Setter for "warehouse" attribute.*/
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for "strongbox" attribute.*/
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
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
            player.setStrongbox(this.strongbox);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
