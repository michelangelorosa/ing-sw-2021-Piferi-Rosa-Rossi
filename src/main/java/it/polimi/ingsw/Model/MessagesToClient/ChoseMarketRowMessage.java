package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Market;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

/**
 * ChoseMarketRowMessage Class defines a response to be sent to the client after
 * a MarketChooseRow request.
 */
public class ChoseMarketRowMessage extends MessageToClient {
    /** A boolean and an int attributes define the row or column chosen by the client */
    private boolean isRow;
    private int rowOrColumn;
    private RedResourceStack temporaryResources;

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

    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        for (Player player : game.getPlayers())
            if(player.getTurnPosition() == this.playerId) {
                player.setPossibleActions(this.possibleActions);
                if(this.error.equals("SUCCESS"))
                    player.setTemporaryResources(this.temporaryResources);
            }

        if(this.isRow)
            ((Market)game.getMarket()).rowChange(this.rowOrColumn);
        else
            ((Market)game.getMarket()).columnChange(this.rowOrColumn);
    }
}
