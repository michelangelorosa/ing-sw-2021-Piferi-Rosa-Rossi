package it.polimi.ingsw.Model.MessagesToClient.OtherMessages;

import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ExceptionMessage Class defines a special message to be sent to the players when an exception is caught internally
 * by the Server.
 * <p>This is needed avoid Server interruptions when an unexpected error occurs.</p>
 * @author redrick99
 */
public class ExceptionMessage extends MessageToClient {
    public ExceptionMessage(String playerNickname) {
        super(playerNickname);
    }

    /**
     * Displays the caught Exception using the Client's User Interface.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        this.displayError(userInteraction);
    }
}
