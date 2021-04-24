package it.polimi.ingsw.View.MessagesToClient;

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
}
