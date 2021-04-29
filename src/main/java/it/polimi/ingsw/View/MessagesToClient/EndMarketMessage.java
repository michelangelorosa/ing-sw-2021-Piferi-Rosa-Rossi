package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

import java.util.ArrayList;

public class EndMarketMessage extends MessageToClient {
    /** All players position onto the faith track is needed to update the clients' View */
    private ArrayList<Integer> playersFaithPosition;

    /**
     * Constructor for EndMarketMessage Class.
     */
    public EndMarketMessage(int playerId) {
        this.actionDone = ActionType.END_MARKET;
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
