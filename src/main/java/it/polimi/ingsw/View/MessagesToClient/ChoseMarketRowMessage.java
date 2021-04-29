package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

public class ChoseMarketRowMessage extends MessageToClient {
    /** A boolean and an int attributes define the row or column chosen by the client */
    private boolean isRow;
    private int rowOrColumn;

    /**
     * Constructor for ChoseMarketRowMessage Class.
     */
    public ChoseMarketRowMessage(int playerId) {
        this.actionDone = ActionType.MARKET_CHOOSE_ROW;
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
