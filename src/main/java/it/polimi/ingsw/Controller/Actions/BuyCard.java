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
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>int "row", "column":  coordinates of the deck the player wants to buy the cards from</li>
 * </ul>
 * @author redrick99
 */
public class
BuyCard extends Action {
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
     * Getter for "row" attribute.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for "column" attribute.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Checks if the specified action is formally correct.
     * @return true if "row" is between 0 and 2 and "column" is between 0 and 3.
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
     * Checks if the specified action is logically applicable.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return false if the Deck from which the player wants to buy the card is Empty.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        return !actionController.getGame().getDevelopmentCardTable().getDeck(row, column).isEmpty();
    }

    /**
     * Controls and executes the action on the Model.
     * <p>Firstly checks if the player has resources to buy the Card and if the Card can
     * fit inside his personal board, then saves data needed later to draw the Card from
     * the Development Card Table.</p>
     * At last, if the Player can buy the Card, a new Payment Cycle starts.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(!this.canDoAction(actionController))
            return ILLEGAL_ACTION;

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
            actionController.getGame().getCurrentPlayer().clearPossibleActions();
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.PAY_RESOURCE_CARD);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
    }

    /**
     * Prepares a BuyCardMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return The created message.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        Game game = actionController.getGame();
        BuyCardMessage message = new BuyCardMessage(game.getCurrentPlayerNickname());
        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

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
