package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.DiscardLeaderCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;

/**
 * DiscardLeaderCard class contains data and methods to resolve a Client request regarding Leader
 * Card discard action.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li> int "number": represents the number of the leader card that the player wants to discard. </li>
 * </ul>
 * @author francescopiferi99
 */
public class DiscardLeaderCard extends Action{

    /**
     * Number is an attribute to distinguish the two leader card a players owns.
     */
    private final int number;

    /**
     * Constructor for DiscardLeaderCard
     * @param number is the number of the leader that the player wants to discard
     */
    public DiscardLeaderCard(int number){
        this.actionType = ActionType.DELETE_LEADERCARD;
        this.number = number;
    }

    /**Getter for Number*/
    public int getNumber() {
        return number;
    }

    /**
     * This method return true if this action can be applied
     * @return boolean, true if the argument of the method are compatible with the game
     * @throws IllegalArgumentException if the number is not 0 or 1
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException{
        if(this.number > 1 || this.number < 0)
            throw new IllegalArgumentException("Not a valid card");

        return true;
    }

    /**
     * This method returns true if the function can be applied, so when the Leader Card is not active and not discarded
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the method can be applied
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        return !(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[this.number].isActive() || actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[this.number].isDiscarded());
    }

    /**
     * After a check to see if the method can be applied, by calling canDoAction, isCorrect and canBeApplied, the card
     * becomes discarded, the player steps ahead on the faith track and DELETE_LEADERCARD is removed from the possible
     * actions.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the method goes well
     */
    @Override
    public String doAction(ActionController actionController) throws IllegalArgumentException{
        if(!this.canDoAction(actionController))
            return ILLEGAL_ACTION;

        this.isCorrect();

        if(!this.canBeApplied(actionController)) {
            this.response = "You cannot discard this leader card";
            return "You cannot discard this leader card";
        }


        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[this.number].discard();
        actionController.getGame().getCurrentPlayer().stepAhead(1); //The player who discards a leader card steps ahead on the faith track
        this.response = "SUCCESS";
        actionController.getGame().getCurrentPlayer().removePossibleAction(ActionType.DELETE_LEADERCARD);
        return "SUCCESS";
    }

    /**
     * Prepares a DiscardLeaderCardMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        Game game = actionController.getGame();
        DiscardLeaderCardMessage message = new DiscardLeaderCardMessage(game.getCurrentPlayerNickname());
        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.setNumber(this.number);
            message.setLeaderCard(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[this.number]);

            for(ActionType type : actionController.getGame().getCurrentPlayer().getPossibleActions())
                message.addPossibleAction(type);
        }
        return message;
    }
}
