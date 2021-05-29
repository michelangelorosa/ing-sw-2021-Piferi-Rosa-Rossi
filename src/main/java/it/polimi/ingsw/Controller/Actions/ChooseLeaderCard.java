package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.GameModel.Player;
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
    public boolean canBeApplied(ActionController actionController) {
        Player player = actionController.getGame().getCurrentPlayer();
        return player.getBoard().getLeaderCards()[leaderCard].isActive() && player.getBoard().getLeaderCards()[leaderCard].getAction() == LeaderCardAction.WHITEMARBLE;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();
        if(!this.canBeApplied(actionController)) {
            response = "Leader Card not active or not of type WHITE MARBLE";
            return "Leader Card not active or not of type WHITE MARBLE";
        }

        actionController.getGame().getMarket().whiteMarbleToResource(actionController.getGame().getCurrentPlayer(), actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[leaderCard]);

        if(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0) {
            response = "Another White Marble";
            return "Another White Marble";
        }
        else {
            response = "SUCCESS";
            actionController.getResetWarehouse().setBackupResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
            return "SUCCESS";
        }
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ChoseLeaderCardMessage message = new ChoseLeaderCardMessage(actionController.getGame().getCurrentPlayerNickname());
        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.setTemporaryResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
            message.addPossibleAction(ActionType.ADD_RESOURCE);
            message.addPossibleAction(ActionType.SWITCH_DEPOT);
            message.addPossibleAction(ActionType.RESET_WAREHOUSE);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_LEADER_CARD);

        return message;
    }
}