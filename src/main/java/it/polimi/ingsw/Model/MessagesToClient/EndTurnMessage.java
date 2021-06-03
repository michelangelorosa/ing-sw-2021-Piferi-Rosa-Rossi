package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * EndTurnMessage Class defines a response message to be sent to a client after
 * an EndTurn request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>String "nextPlayerName": contains the name of the player whose turn is next</li>
 * </ul>
 * @author redrick99
 */
public class EndTurnMessage extends MessageToClient {
    private String nextPlayerNickname;

    /**
     * Constructor for EndTurnMessage Class.
     */
    public EndTurnMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_TURN;
    }

    /**
     * Setter for "nextPlayerNickname" attribute.
     */
    public void setNextPlayerNickname(String nextPlayerNickname) {
        this.nextPlayerNickname = nextPlayerNickname;
    }

    /**
     * Getter for "nextPlayerNickname" attribute.
     */
    public String getNextPlayerNickname() {
        return nextPlayerNickname;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);
        this.displayAction(userInteraction);
    }
}
