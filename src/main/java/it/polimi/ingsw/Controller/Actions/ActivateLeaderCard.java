package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Game;

/**
 * ActivateLeaderClass contains data and methods to resolve a Client request regarding Leader
 * Card activation.
 */
public class ActivateLeaderCard {
    private final ActionType actionType;
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
    public String doActivateLeaderCard(Game game) {
        this.isCorrect();
        if(!this.canBeApplied(game))
            return "Leader Card has already been activated";

        Board board = game.getCurrentPlayer().getBoard();

        if(!board.canActivateLeaderCard(board.getLeaderCards()[this.leaderCard]))
            return "Not enough resources to activate Leader Card";
        else {
            board.activateLeaderCard(board.getLeaderCards()[this.leaderCard]);
            return "SUCCESS";
        }
    }
}
