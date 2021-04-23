package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * MarketChooseTow Class contains data and methods to resolve a Client request regarding Market
 * shopping.
 */
public class MarketChooseRow extends Action {
    /** True if the player chose a Row, false if the player chose a Column */
    private final boolean row;
    private final int rowOrColumnNumber;

    /**
     * Constructor for MarketChooseRow Class.
     */
    public MarketChooseRow(boolean row, int rowOrColumnNumber) {
        this.actionType = ActionType.MARKET_CHOOSE_ROW;
        this.row = row;
        this.rowOrColumnNumber = rowOrColumnNumber;
    }

    /**
     * Getter for "actionType" in Action super Class.
     */
    public ActionType getAction() {
        return actionType;
    }

    /**
     * Getter for "row" in MarketChooseRow Class.
     */
    public boolean isRow() {
        return row;
    }

    /**
     * Getter for "rowOrColumnNumber" in MarketChooseRow Class.
     */
    public int getRowOrColumnNumber() {
        return rowOrColumnNumber;
    }

    /**
     * This method checks if the input sent to the server is correct by assuring that the row or
     * column index does not go out of bounds.
     * @throws IllegalArgumentException if the row or column index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(rowOrColumnNumber < 0 || (row && rowOrColumnNumber > 2) || (!row && rowOrColumnNumber > 3))
            throw new IllegalArgumentException("Row or Column index out of bounds.");

        return true;
    }

    /**
     * Method used to check if the action is logically applicable.
     */
    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();

        if(this.row)
            game.getMarket().rowToResources(this.getRowOrColumnNumber(), game.getCurrentPlayer());
        else
            game.getMarket().columnToResources(this.getRowOrColumnNumber(), game.getCurrentPlayer());

        resetWarehouse.setBackupWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        resetWarehouse.setBackupResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack());

        this.response = this.leaderCardCheck(game);
        return response;
    }

    /**
     * Method used to check if and how many Leader Cards of type WHITE MARBLE the player has.
     * @param game Is the instance of the game used to get the current player.
     * @return "Choose Leader Card" if the player has two active WHITE MARBLE ability Leader Cards.
     */
    public String leaderCardCheck(Game game) {
        LeaderCard[] leaderCards = game.getCurrentPlayer().getBoard().getLeaderCards();

        if(leaderCards[0].isActive() && leaderCards[0].getAction() == LeaderCardAction.WHITEMARBLE && leaderCards[1].isActive() && leaderCards[1].getAction() == LeaderCardAction.WHITEMARBLE)
            return "Choose Leader Card";

        else if(leaderCards[0].isActive() && leaderCards[0].getAction() == LeaderCardAction.WHITEMARBLE && (!leaderCards[1].isActive() || leaderCards[1].getAction() != LeaderCardAction.WHITEMARBLE)) {
            while(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
                game.getMarket().whiteMarbleToResource(game.getCurrentPlayer(), leaderCards[0]);
            return "SUCCESS";
        }

        else if(leaderCards[1].isActive() && leaderCards[1].getAction() == LeaderCardAction.WHITEMARBLE && (!leaderCards[0].isActive() || leaderCards[0].getAction() != LeaderCardAction.WHITEMARBLE)) {
            while(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
                game.getMarket().whiteMarbleToResource(game.getCurrentPlayer(), leaderCards[1]);
            return "SUCCESS";
        }

        else {
            game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryWhiteMarbles(0);
            return "SUCCESS";
        }
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @param game Current instance of the Game being played.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(Game game) {
        ChoseMarketRowMessage message = new ChoseMarketRowMessage(game.getCurrentPlayerIndex());
        message.setError(this.response);
        message.setRow(this.row);
        message.setRowOrColumn(this.rowOrColumnNumber);
        if(this.response.equals("SUCCESS")) {
            message.addPossibleAction(ActionType.ADD_RESOURCE);
            message.addPossibleAction(ActionType.RESET_WAREHOUSE);
            message.addPossibleAction(ActionType.SWITCH_DEPOT);
            message.addPossibleAction(ActionType.END_MARKET);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_LEADER_CARD);

        return message;
    }
}
