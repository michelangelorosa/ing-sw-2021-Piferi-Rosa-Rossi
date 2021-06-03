package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ChoseLeaderCardMessage Class contains data for a response message to be sent to the client
 * after a ChooseLeaderCard request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedResourceStack "temporaryResources": contains the number of resources which the player has
 *     to add to his Warehouse</li>
 * </ul>
 * @author redrick99
 */
public class ChoseLeaderCardMessage extends MessageToClient {
    RedResourceStack temporaryResources;

    /**
     * Constructor for ChoseLeaderCardMessage Class.
     */
    public ChoseLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_LEADER_CARD;
    }

    /**
     * Setter for "temporaryResources" attribute.
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

        if(this.success()) {
            player.setTemporaryResources(temporaryResources);
            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
