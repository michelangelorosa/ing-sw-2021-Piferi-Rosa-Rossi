package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class MarketChooseRow extends Action implements ActionInterface {
    private final boolean row;
    private final int rowOrColumnNumber;

    public MarketChooseRow(boolean row, int rowOrColumnNumber) {
        this.actionType = ActionType.MARKET_CHOOSE_ROW;
        this.row = row;
        this.rowOrColumnNumber = rowOrColumnNumber;
    }

    public ActionType getAction() {
        return actionType;
    }

    public boolean isRow() {
        return row;
    }

    public int getRowOrColumnNumber() {
        return rowOrColumnNumber;
    }

    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(rowOrColumnNumber < 0 || (row && rowOrColumnNumber > 2) || (!row && rowOrColumnNumber > 3))
            throw new IllegalArgumentException("Row or Column index out of bounds.");

        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();

        if(this.row)
            game.getMarket().rowToResources(this.getRowOrColumnNumber(), game.getCurrentPlayer());
        else
            game.getMarket().columnToResources(this.getRowOrColumnNumber(), game.getCurrentPlayer());

        resetWarehouse.setBackupWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        resetWarehouse.setBackupResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack());

        return this.leaderCardCheck(game);
    }

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
}
