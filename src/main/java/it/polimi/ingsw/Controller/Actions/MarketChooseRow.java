package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * MarketChooseTow Class contains data and methods to resolve a Client request regarding Market
 * shopping.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>boolean "row": set to true if the player chose a Row from the Market, false if he chose a Column</li>
 *     <li>int "rowOrColumnNumber": indicates the row or column the player chose</li>
 *     <li>ResourceStack "temporaryResource": contains resources gathered when choosing a row or a column
 *     from the market (needed to start a <i>Add Cycle</i>)</li>
 * </ul>
 * @author redrick99
 */
public class MarketChooseRow extends Action {
    /** True if the player chose a Row, false if the player chose a Column */
    private final boolean row;
    private final int rowOrColumnNumber;
    private ResourceStack temporaryResource;

    /**
     * Constructor for MarketChooseRow Class.
     */
    public MarketChooseRow(boolean row, int rowOrColumnNumber) {
        this.actionType = ActionType.MARKET_CHOOSE_ROW;
        this.row = row;
        this.rowOrColumnNumber = rowOrColumnNumber;
        this.temporaryResource = null;
    }

    /**
     * Getter for "row" attribute.
     */
    public boolean isRow() {
        return row;
    }

    /**
     * Getter for "rowOrColumnNumber" attribute.
     */
    public int getRowOrColumnNumber() {
        return rowOrColumnNumber;
    }

    /**
     * Checks if the input sent to the server is correct by assuring that the row or
     * column index does not go out of bounds.
     * @return true if the row is between 0 and 2 or if the column is between 0 and 3.
     * @throws IllegalArgumentException if the row or column index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(rowOrColumnNumber < 0 || (row && rowOrColumnNumber > 2) || (!row && rowOrColumnNumber > 3))
            throw new IllegalArgumentException("Row or Column index out of bounds.");

        return true;
    }

    /**
     * Controls and executes the action on the Model. It saves resources got from the Market in a temporary ResourceStack
     * inside the player's board to begin a <i>Add Cycle</i>.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) throws IllegalArgumentException{
        if(!this.canDoAction(actionController))
            return ILLEGAL_ACTION;

        this.isCorrect();

        if(this.row)
            actionController.getGame().getMarket().rowToResources(this.rowOrColumnNumber, actionController.getGame().getCurrentPlayer());
        else
            actionController.getGame().getMarket().columnToResources(this.rowOrColumnNumber, actionController.getGame().getCurrentPlayer());

        actionController.getResetWarehouse().setBackupWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        actionController.getResetWarehouse().setBackupResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack());

        this.response = this.leaderCardCheck(actionController);
        if(this.response.equals("SUCCESS")) {
            actionController.getGame().getCurrentPlayer().clearPossibleActions();

            if(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().hasPayed()) {
                this.response = "Has Payed";
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
            }
            else {
                this.temporaryResource = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay();

                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ADD_RESOURCE);
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.RESET_WAREHOUSE);
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.SWITCH_DEPOT);
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_MARKET);
            }
        }

        if(this.response.equals("SUCCESS") && actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().hasPayed())
            this.response = "Has Payed";
        return response;
    }

    /**
     * Checks if and how many Leader Cards of type WHITE MARBLE the player has.
     * <p>If the player has more than one active card of type WHITE MARBLE, he has to choose which card he wants to use
     * for a Market interaction for <b>each</b> white marble.</p>
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "Choose Leader Card" if the player has two active WHITE MARBLE ability Leader Cards.
     */
    public String leaderCardCheck(ActionController actionController) {
        LeaderCard[] leaderCards = actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards();

        if(leaderCards[0].isActive() && leaderCards[0].getAction() == LeaderCardAction.WHITEMARBLE && leaderCards[1].isActive() && leaderCards[1].getAction() == LeaderCardAction.WHITEMARBLE && actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0) {
            actionController.getGame().getCurrentPlayer().clearPossibleActions();
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_LEADER_CARD);
            return "Choose Leader Card";
        }

        else if(leaderCards[0].isActive() && leaderCards[0].getAction() == LeaderCardAction.WHITEMARBLE && (!leaderCards[1].isActive() || leaderCards[1].getAction() != LeaderCardAction.WHITEMARBLE)) {
            while(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
                actionController.getGame().getMarket().whiteMarbleToResource(actionController.getGame().getCurrentPlayer(), leaderCards[0]);
            return "SUCCESS";
        }

        else if(leaderCards[1].isActive() && leaderCards[1].getAction() == LeaderCardAction.WHITEMARBLE && (!leaderCards[0].isActive() || leaderCards[0].getAction() != LeaderCardAction.WHITEMARBLE)) {
            while(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
                actionController.getGame().getMarket().whiteMarbleToResource(actionController.getGame().getCurrentPlayer(), leaderCards[1]);
            return "SUCCESS";
        }

        else {
            actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().setTemporaryWhiteMarbles(0);
            return "SUCCESS";
        }
    }

    /**
     * Prepares a ChoseMarketRowMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ChoseMarketRowMessage message = new ChoseMarketRowMessage(actionController.getGame().getCurrentPlayerNickname());
        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

        message.setError(this.response);
        message.setMarket(actionController.getGame().getMarket());
        message.setTemporaryResources(this.temporaryResource);
        if(this.response.equals("SUCCESS")) {
            message.addPossibleAction(ActionType.ADD_RESOURCE);
            message.addPossibleAction(ActionType.RESET_WAREHOUSE);
            message.addPossibleAction(ActionType.SWITCH_DEPOT);
            message.addPossibleAction(ActionType.END_MARKET);
        }
        else if(this.response.equals("Has Payed")) {
            message.addPossibleAction(ActionType.END_TURN);
            if(actionController.getGame().getCurrentPlayer().canDo(ActionType.ACTIVATE_LEADERCARD))
                message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_LEADER_CARD);

        return message;
    }
}
