package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;

/**
 * EndTurnMessage Class defines a response message to be sent to a client after
 * an EndTurn request.
 */
public class EndTurnMessage extends MessageToClient {
    /** the next player's Id is needed to update the client's View */
    private String nextPlayerNickname;

    /**
     * Constructor for EndTurnMessage Class.
     */
    public EndTurnMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_TURN;
    }

    /**
     * Setter for "nextPlayerId" attribute in EndTurnMessage Class.
     */
    public void setNextPlayerNickname(String nextPlayerNickname) {
        this.nextPlayerNickname = nextPlayerNickname;
    }

    /**
     * Getter for "nextPlayerId" attribute in EndTurnMessage Class.
     */
    public String getNextPlayerNickname() {
        return nextPlayerNickname;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {

    }
}
