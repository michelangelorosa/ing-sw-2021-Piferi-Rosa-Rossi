package it.polimi.ingsw.Model.MessagesToClient.OtherMessages;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.View.Utility.ANSIfont;

/**
 * DisconnectedMessage Class defines a message to be sent to all clients ONLY if the player who disconnected
 * from the game <b>was the one currently playing</b>.
 * @author redrick99
 */
public class DisconnectedMessage extends MessageToClient {

    /**
     * Constructor for DisconnectedMessage Class.
     * @param actionController Used to get the Model's game info.
     * @param disconnectedPlayer Name of the player who disconnected.
     */
    public DisconnectedMessage(ActionController actionController, String disconnectedPlayer) {
        this.playerNickname = actionController.getGame().getCurrentPlayerNickname();
        this.possibleActions = actionController.getGame().getCurrentPlayer().getPossibleActions();
        this.actionDone = ActionType.DISCONNECTION;
        this.error = "Player " + ANSIfont.ITALIC + ANSIfont.BOLD + disconnectedPlayer + ANSIfont.RESET + " disconnected";
    }

    /**
     * Displays an error informing the players about who disconnected and makes the next non disconnected player
     * play his turn.
     * @param userInteraction Class containing the instance of the reduced Game seen by the players.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        displayError(userInteraction);
    }

}
