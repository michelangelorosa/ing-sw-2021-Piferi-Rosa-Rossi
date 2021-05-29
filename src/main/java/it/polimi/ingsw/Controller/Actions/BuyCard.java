package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardSlots;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.ResourceManager;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * BuyCard Class contains data and methods to resolve a Client's request when buying a card
 * from the DevelopmentCardTable.
 */
public class
BuyCard extends Action {
    /** row and column attributes are the coordinates of the deck the player wants to buy the cards from */
    private final int row;
    private final int column;

    /**
     * Constructor for BuyCard Class.
     */
    public BuyCard(int row, int column) {
        this.actionType = ActionType.BUY_CARD;
        this.row = row;
        this.column = column;
    }

    /**
     * Getter for "actionType" attribute in BuyCard Class.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "row" attribute in BuyCard Class.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for "column" attribute in BuyCard Class.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Method used to check if the specified action is formally correct.
     * @throws IllegalArgumentException if the row or column indexes are out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(row < 0 || row > 2)
            throw new IllegalArgumentException("Row index out of bounds.");
        else if(column < 0 || column > 3)
            throw new IllegalArgumentException("Column index out of bounds.");
        return true;
    }

    /**
     * Method used to check if the specified action is logically correct.
     * @return false if the Deck specified by the Client is Empty.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        return !actionController.getGame().getDevelopmentCardTable().getDeck(row, column).isEmpty();
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();

        if(!this.canBeApplied(actionController)) {
            this.response = "This Deck is empty";
            return "This Deck is empty";
        }
        DevelopmentCard card;
        card = actionController.getGame().getDevelopmentCardTable().getTopCardFromDeck(this.row, this.column);
        ResourceManager resourceManager = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager();
        DevelopmentCardSlots developmentCardSlots = actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots();

        if(!resourceManager.cardIsBuyable(card, actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards())) {
            this.response = "Not enough resources to buy Card";
            return "Not enough resources to buy Card";
        }
        else if (!developmentCardSlots.canAdd(card)) {
            this.response = "Card does not fit inside Personal Board";
            return "Card does not fit inside Personal Board";
        }
        else {
            actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(card.getCost());
            actionController.getChooseCardSlot().setRowCardToBuy(this.row);
            actionController.getChooseCardSlot().setColumnCardToBuy(this.column);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        Game game = actionController.getGame();
        BuyCardMessage message = new BuyCardMessage(game.getCurrentPlayerNickname());
        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.addPossibleAction(ActionType.PAY_RESOURCE_CARD);
            message.setTemporaryResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
        }
        else {
            message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
            message.addPossibleAction(ActionType.BUY_CARD);
            message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        }

        return message;
    }
}
