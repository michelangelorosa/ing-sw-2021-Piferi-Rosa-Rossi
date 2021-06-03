package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ActivateProductionMessage Class defines methods and attributes to update the Client's View after
 * a ActivateProduction Action.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedResourceStack "resourcesToPay": reduced stack containing the number of resources
 *     the player has to pay</li>
 * </ul>
 * @author redrick99
 */
public class ActivateProductionMessage extends MessageToClient {
    private RedResourceStack resourcesToPay;

    /**
     * Constructor for ActivateProduction Action.
     */
    public ActivateProductionMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ACTIVATE_PRODUCTION;
    }

    /**
     * Getter for "resourcesToPay" attribute.
     */
    public RedResourceStack getResourcesToPay() {
        return resourcesToPay;
    }

    /**
     * Setter for "resourcesToPay" attribute.
     */
    public void setResourcesToPay(RedResourceStack resourcesToPay) {
        this.resourcesToPay = resourcesToPay;
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
            player.setTemporaryResources(this.resourcesToPay);
            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }

}
