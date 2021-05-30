package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.GameModel.Board;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ActivateLeaderClass contains data and methods to resolve a Client request regarding Leader
 * Card activation.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li> int "leaderCard": contains the position, inside the player's deck, of the Leader Card. </li>
 * </ul>
 * @author redrick99
 */
public class ActivateLeaderCard extends Action {

    private final int leaderCard;

    /**
     * Constructor for ActivateLeaderCardClass.
     */
    public ActivateLeaderCard(int leaderCard) {
        this.actionType = ActionType.ACTIVATE_LEADERCARD;
        this.leaderCard = leaderCard;
    }

    /**
     * Getter for "leaderCard" attribute.
     */
    public int getLeaderCard() {
        return leaderCard;
    }

    /**
     * Checks if the input sent to the server is correct by assuring that the Leader Card's
     * position corresponds to an existing card inside the player's hand.
     * @return true if the message is correct.
     * @throws IllegalArgumentException if "leaderCard" is not 0 nor 1.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(leaderCard != 0 && leaderCard != 1)
            throw new IllegalArgumentException("Leader Card index out of bounds.");
        return true;
    }

    /**
     * This method checks if the input sent to the server is logically applicable by assuring that the
     * indicated card is not already active.
     * @return false if the Leader Card was already active, true if not.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        if(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[this.leaderCard].isActive())
            return false;

        return true;
    }

    /**
     * Activates the player's Leader Card if the player has all the requirements needed.
     * @return a String containing an error message or a SUCCESS statement.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();
        if(!this.canBeApplied(actionController)) {
            this.response = "Leader Card has already been activated";
            return "Leader Card has already been activated";
        }

        Board board = actionController.getGame().getCurrentPlayer().getBoard();

        if(!board.canActivateLeaderCard(board.getLeaderCards()[this.leaderCard])) {
            this.response = "Not enough resources to activate Leader Card";
            return "Not enough resources to activate Leader Card";
        }
        else {
            board.activateLeaderCard(board.getLeaderCards()[this.leaderCard]);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
    }

    /**
     * Prepares a ActivateLeaderCardMessage type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ActivateLeaderCardMessage message = new ActivateLeaderCardMessage(actionController.getGame().getCurrentPlayerNickname());
        message.setError(this.response);
        message.setLeaderCardPosition(this.leaderCard);

        if(this.response.equals("SUCCESS"))
            message.setLeaderCard(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[this.leaderCard]);

        return message;
    }
}
