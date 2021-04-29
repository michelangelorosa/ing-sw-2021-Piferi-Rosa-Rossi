package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ChooseLeaderCard Class contains data and methods to resolve a Client request regarding Leader Card
 * choice when buying a white marble from the market.
 */
public class ChooseLeaderCard extends Action {
    private final int leaderCard;

    /**
     * Constructor for ChooseLeaderCard Class.
     */
    public ChooseLeaderCard(int leaderCard) {
        this.actionType = ActionType.CHOOSE_LEADER_CARD;
        this.leaderCard = leaderCard;
    }

    /**
     * Getter for "actionType" attribute in ChooseLeaderCard Class.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "leaderCard" attribute in ChooseLeaderCard Class.
     */
    public int getLeaderCard() {
        return leaderCard;
    }

    /**
     * This method checks if the input sent to the server is correct by assuring that the Leader Card
     * index does not go out of bounds.
     * @throws IllegalArgumentException if the row or column index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(leaderCard != 0 && leaderCard != 1)
            throw new IllegalArgumentException("Leader Card index out of bounds.");
        return true;
    }

    /**
     * Method used to check if the action is logically applicable.
     */
    @Override
    public boolean canBeApplied(Game game) {
        Player player = game.getCurrentPlayer();
        return player.getBoard().getLeaderCards()[leaderCard].isActive() && player.getBoard().getLeaderCards()[leaderCard].getAction() == LeaderCardAction.WHITEMARBLE;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();
        if(!this.canBeApplied(game)) {
            response = "Leader Card not active or not of type WHITE MARBLE";
            return "Leader Card not active or not of type WHITE MARBLE";
        }

        game.getMarket().whiteMarbleToResource(game.getCurrentPlayer(), game.getCurrentPlayer().getBoard().getLeaderCards()[leaderCard]);

        if(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0) {
            response = "Another White Marble";
            return "Another White Marble";
        }
        else {
            response = "SUCCESS";
            resetWarehouse.setBackupResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
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
        ChoseLeaderCardMessage message = new ChoseLeaderCardMessage(game.getCurrentPlayerIndex());
        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.setTemporaryResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
            message.addPossibleAction(ActionType.ADD_RESOURCE);
            message.addPossibleAction(ActionType.SWITCH_DEPOT);
            message.addPossibleAction(ActionType.RESET_WAREHOUSE);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_LEADER_CARD);

        return message;
    }
}