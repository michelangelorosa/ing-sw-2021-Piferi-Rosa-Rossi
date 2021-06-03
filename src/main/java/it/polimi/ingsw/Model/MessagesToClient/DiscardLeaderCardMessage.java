package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * DiscardLeaderMessage Class defines a response message to be sent to a client after
 * a DiscardLeader Action request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>int "number": position of the card which the player wanted to discard</li>
 *     <li>RedLeaderCard "leaderCard": Reduced Leader Card which the player has discarded</li>
 *
 * </ul>
 * @author francescopiferi99
 */
public class DiscardLeaderCardMessage extends MessageToClient{
    private int number;
    private RedLeaderCard leaderCard;

    /**
     * Constructor for DiscardLeaderMessage Class.
     */
    public DiscardLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.DELETE_LEADERCARD;
    }

    /**
     * Getter for "number" attribute.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for "number" attribute.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for "leaderCard" attribute.
     */
    public RedLeaderCard getLeaderCard() {
        return leaderCard;
    }

    /**
     * Setter for "leaderCard" attribute.
     */
    public void setLeaderCard(RedLeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = this.getPlayer(userInteraction);

        if(this.success()) {
            player.getLeaderCards()[this.number] = leaderCard;
            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
