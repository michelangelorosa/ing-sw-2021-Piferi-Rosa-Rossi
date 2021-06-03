package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * InitChoseLeaderCards Class defines a response message to be sent to a client after
 * an InitChooseLeaderCards Action request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedWarehouse "warehouse": Warehouse to be set to the corresponding player inside
 *     the reduced game</li>
 * </ul>
 * @author redrick99
 */
public class InitChoseLeaderCardsMessage extends MessageToClient{
    RedLeaderCard leaderCard1;
    RedLeaderCard leaderCard2;

    /**
     * Constructor for InitChoseLeaderCard Class.
     */
    public InitChoseLeaderCardsMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.INIT_CHOOSE_LEADER_CARDS;
    }

    /**
     * Getter for "leaderCard1" attribute.
     */
    public RedLeaderCard getLeaderCard1() {
        return leaderCard1;
    }

    /**
     * Setter for "leaderCard1" attribute.
     */
    public void setLeaderCard1(RedLeaderCard leaderCard1) {
        this.leaderCard1 = leaderCard1;
    }

    /**
     * Getter for "leaderCard2" attribute.
     */
    public RedLeaderCard getLeaderCard2() {
        return leaderCard2;
    }

    /**
     * Setter for "leaderCard2" attribute.
     */
    public void setLeaderCard2(RedLeaderCard leaderCard2) {
        this.leaderCard2 = leaderCard2;
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
            player.getLeaderCards()[0] = leaderCard1;
            player.getLeaderCards()[1] = leaderCard2;

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
