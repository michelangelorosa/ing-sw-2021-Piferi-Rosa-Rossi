package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.ResourceStack;

/**
 * ChoseMarketRowMessage Class defines a response to be sent to the client after
 * a MarketChooseRow request.
 */
public class ChoseMarketRowMessage extends MessageToClient {
    /** A boolean and an int attributes define the row or column chosen by the client */
    private boolean isRow;
    private int rowOrColumn;
    private it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources;

    /**
     * Constructor for ChoseMarketRowMessage Class.
     */
    public ChoseMarketRowMessage(int playerId) {
        this.actionDone = ActionType.MARKET_CHOOSE_ROW;
        this.playerId = playerId;
    }

    /**
     * Setter for "row" attribute in ChoseMarketRowMessage Class.
     */
    public void setRow(boolean row) {
        isRow = row;
    }

    /**
     * Getter for "row" attribute in ChoseMarketRowMessage Class.
     */
    public boolean isRow() {
        return isRow;
    }

    /**
     * Setter for "rowOrColumn" attribute in ChoseMarketRowMessage Class.
     */
    public void setRowOrColumn(int rowOrColumn) {
        this.rowOrColumn = rowOrColumn;
    }

    /**
     * Getter for "rowOrColumn" attribute in ChoseMarketRowMessage Class.
     */
    public int getRowOrColumn() {
        return rowOrColumn;
    }

    public it.polimi.ingsw.View.ReducedModel.ResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
