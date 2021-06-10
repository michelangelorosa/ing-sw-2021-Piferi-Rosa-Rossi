package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
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

    /**
     * Constructor for InitChoseLeaderCard Class.
     */
    public InitChoseLeaderCardsMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.INIT_CHOOSE_LEADER_CARDS;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);


    }
}
