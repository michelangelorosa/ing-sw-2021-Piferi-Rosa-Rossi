package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ActivateLeaderClass contains data and methods to resolve a Client request regarding Leader
 * Card activation.
 */
public class ActivateLeaderCard extends Action {

    /** "leaderCard" is an int type attribute containing the card's position int the player's Leader Card deck. */
    private final int leaderCard;

    /**
     * Constructor for ActivateLeaderCardClass.
     */
    public ActivateLeaderCard(int leaderCard) {
        this.actionType = ActionType.ACTIVATE_LEADERCARD;
        this.leaderCard = leaderCard;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getLeaderCard() {
        return leaderCard;
    }

    /**
     * This method checks if the input sent to the server is correct by assuring that the Leader Card's
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
     * This method checks if the input sent to the server is logically applicable.
     * @param game is the instance of the Game which is being played.
     * @return false if the Leader Card was already active, true if not.
     */
    @Override
    public boolean canBeApplied(Game game) {
        if(game.getCurrentPlayer().getBoard().getLeaderCards()[this.leaderCard].isActive())
            return false;

        return true;
    }

    /**
     * This method is used to actually activate the player's Leader Card.
     * @param game is the instance of the Game which is being played.
     * @return a String containing an error message or a SUCCESS statement.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();
        if(!this.canBeApplied(game)) {
            this.response = "Leader Card has already been activated";
            return "Leader Card has already been activated";
        }

        Board board = game.getCurrentPlayer().getBoard();

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

    @Override
    public MessageToClient messagePrepare(Game game) {
        ActivateLeaderCardMessage message = new ActivateLeaderCardMessage(game.getCurrentPlayerIndex());
        message.setError(this.response);
        message.setLeaderCardPosition(this.leaderCard);

        return message;
    }
}
