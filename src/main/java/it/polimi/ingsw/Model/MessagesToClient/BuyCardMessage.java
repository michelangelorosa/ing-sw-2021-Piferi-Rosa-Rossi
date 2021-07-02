package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * BuyCardMessage Class contains data for a response message to be sent to the client
 * after a BuyCard request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedResourceStack "temporaryResources": reduced stack containing the number of resources the player
 *     has to pay</li>
 * </ul>
 */
public class BuyCardMessage extends MessageToClient{
    RedResourceStack temporaryResources;

    /**
     * Constructor for BuyCardMessage Class.
     */
    public BuyCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.BUY_CARD;
    }

    /**
     * Getter for "temporaryResources" attribute.
     */
    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /**
     * Getter for "temporaryResources" attribute.
     */
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
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
            player.setTemporaryResources(this.temporaryResources);
            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
