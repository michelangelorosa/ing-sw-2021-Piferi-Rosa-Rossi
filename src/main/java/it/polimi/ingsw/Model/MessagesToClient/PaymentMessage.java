package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * PaymentMessage Class defines a response message to be sent to a client after
 * a generic PayResource Action request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": Warehouse to be set to the corresponding player inside
 *     the reduced game</li>
 *     <li>RedStrongbox "strongbox": Strongbox to be set to the corresponding player inside
 *     the reduced game</li>
 *     <li>temporaryResources "temporaryResources": contains the number of resources which are
 *     yet to be sold</li>
 * </ul>
 * @author redrick99
 */
public class PaymentMessage extends MessageToClient {
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;
    private RedResourceStack temporaryResources;

    /**Constructor for PaymentMessage Class.*/
    public PaymentMessage(String playerNickname) {
        super(playerNickname);
    }

    /**Setter for "actionDone" attribute.*/
    public void setActionDone(ActionType actionType) {
        this.actionDone = actionType;
    }

    /**Setter for "warehouse" attribute.*/
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for "strongbox" attribute.*/
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**Setter for "temporaryResources" attribute.*/
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**Getter for "warehouse" attribute.*/
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    /**Getter for "strongbox" attribute.*/
    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    /**Getter for "temporaryResources" attribute.*/
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

        if(this.error.equals("HasToPay")) {
            player.setWarehouse(this.warehouse);
            player.setStrongbox(this.strongbox);
            player.setTemporaryResources(this.temporaryResources);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);

    }
}
