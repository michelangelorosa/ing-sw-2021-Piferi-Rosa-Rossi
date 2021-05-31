package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ChooseLeaderCard Class contains data and methods to resolve a Client request regarding Leader Card
 * choice when buying a white marble from the market.
 * <p>Attributes:
 * <ul>
 *     <li>int "leaderCard": indicates the position, inside the player's deck, of the Leader Card
 *     he wants to choose to convert a White Marble</li>
 * </ul>
 * @author redrick99
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
     * Getter for "leaderCard" attribute in ChooseLeaderCard Class.
     */
    public int getLeaderCard() {
        return leaderCard;
    }

    /**
     * Checks if the input sent to the server is correct by assuring that the Leader Card
     * index does not go out of bounds.
     * @return true if "leaderCard" is between 0 and 1.
     * @throws IllegalArgumentException if the row or column index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(leaderCard != 0 && leaderCard != 1)
            throw new IllegalArgumentException("Leader Card index out of bounds.");
        return true;
    }

    /**
     * Checks if the action is logically applicable.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the chosen Leader Card is of type WHITE_MARBLE and active.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        Player player = actionController.getGame().getCurrentPlayer();
        return player.getBoard().getLeaderCards()[leaderCard].isActive() && player.getBoard().getLeaderCards()[leaderCard].getAction() == LeaderCardAction.WHITEMARBLE;
    }

    /**
     * Controls and executes the action on the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(!this.canDoAction(actionController))
            return ILLEGAL_ACTION;

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
            actionController.getGame().getCurrentPlayer().clearPossibleActions();
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ADD_RESOURCE);
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.SWITCH_DEPOT);
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.RESET_WAREHOUSE);
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_MARKET);
            return "SUCCESS";
        }
    }

    /**
     * Prepares a ChoseLeaderCardMessage MessageToClient type object to be sent to the Client.
     * <p>If there are more White Marbles for which the player has to choose a Leader Card,
     * the player's next possible action will be forced to "CHOOSE_LEADER_CARD"</p>
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ChoseLeaderCardMessage message = new ChoseLeaderCardMessage(actionController.getGame().getCurrentPlayerNickname());
        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.setTemporaryResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
            message.addPossibleAction(ActionType.ADD_RESOURCE);
            message.addPossibleAction(ActionType.SWITCH_DEPOT);
            message.addPossibleAction(ActionType.RESET_WAREHOUSE);
            message.addPossibleAction(ActionType.END_MARKET);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_LEADER_CARD);

        return message;
    }
}