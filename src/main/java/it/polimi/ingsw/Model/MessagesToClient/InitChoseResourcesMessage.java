package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * InitChoseResources Class defines a response message to be sent to a client after
 * an InitChooseResources Action request.
 * @author redrick99
 */
public class InitChoseResourcesMessage extends MessageToClient {

    /**
     * Constructor for InitChoseResourcesMessage.
     */
    public InitChoseResourcesMessage(String playerNickname) {
        super(playerNickname);
        this.playerNickname = "";
        this.actionDone = ActionType.INIT_CHOOSE_RESOURCES;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        if(!success()) {
            userInteraction.nextAction(UIActions.INITIAL_CHOOSE_RESOURCES_ERROR);
        }
    }
}
