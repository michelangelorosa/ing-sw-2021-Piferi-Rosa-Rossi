package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.DevelopmentCard;
import it.polimi.ingsw.Model.DevelopmentCardSlots;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ResourceManager;

public class BuyCard extends Action implements ActionInterface{
    private final int row;
    private final int column;

    public BuyCard(int row, int column) {
        this.actionType = ActionType.BUY_CARD;
        this.row = row;
        this.column = column;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(row < 0 || row > 2)
            throw new IllegalArgumentException("Row index out of bounds.");
        else if(column < 0 || column > 3)
            throw new IllegalArgumentException("Column index out of bounds.");
        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();

        DevelopmentCard card;
        card = game.getDevelopmentCardTable().getTopCardFromDeck(this.row, this.column);
        ResourceManager resourceManager = game.getCurrentPlayer().getBoard().getResourceManager();
        DevelopmentCardSlots developmentCardSlots = game.getCurrentPlayer().getBoard().getDevelopmentCardSlots();

        if(!resourceManager.cardIsBuyable(card, game.getCurrentPlayer().getBoard().getLeaderCards()))
            return "Not enough resources to buy Card";
        else if (!developmentCardSlots.canAdd(card))
            return "Card does not fit inside Personal Board";
        else {
            game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(card.getCost());
            chooseCardSlot.setRowCardToBuy(this.row);
            chooseCardSlot.setColumnCardToBuy(this.column);
            return "SUCCESS";
        }
    }
}
