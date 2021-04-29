package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

public class EndTurnMessage extends MessageToClient {
    /** the next player's Id is needed to update the client's View */
    private int nextPlayerId;

    /**
     * Constructor for EndTurnMessage Class.
     */
    public EndTurnMessage(int playerId) {
        this.actionDone = ActionType.END_TURN;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {

    }
}
