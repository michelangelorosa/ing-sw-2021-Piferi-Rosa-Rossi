package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ActivateLeaderCardMessage Class defines methods and attributes to update the Client's View after
 * a ActivateLeaderCard Action.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>int "leaderCardPosition": position, inside the player's array of Leader Cards, of the Leader Card
 *     the player wanted to activate</li>
 *     <li>RedLeaderCard "leaderCard": Leader Card which the player wanted to activate</li>
 *     <li>Warehouse "warehouse": warehouse to be set on the player's view</li>
 * </ul>
 * @author redrick99
 */
public class ActivateLeaderCardMessage extends MessageToClient {
    private int leaderCardPosition;
    private RedLeaderCard leaderCard;
    private Warehouse warehouse;

    /**
     * Constructor for ActivateLeaderCardMessage Class.
     */
    public ActivateLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ACTIVATE_LEADERCARD;
    }

    /**
     * Getter for "leaderCardPosition" attribute.
     */
    public int getLeaderCardPosition() {
        return leaderCardPosition;
    }

    /**
     * Setter for "leaderCardPosition" attribute.
     */
    public void setLeaderCardPosition(int leaderCardPosition) {
        this.leaderCardPosition = leaderCardPosition;
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
     * Getter for "warehouse" attribute.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Setter for "warehouse" attribute.
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
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
            player.getLeaderCards()[this.leaderCardPosition] = this.leaderCard;
            if(this.leaderCard.getAction() == LeaderCardAction.EXTRADEPOT)
                player.setWarehouse(this.warehouse);
            this.displayAction(userInteraction);
        }
        else {
            this.displayError(userInteraction);
        }
    }
}
