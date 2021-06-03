package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EndMarketMessage Class defines a response message to be sent to the client after
 * a EndMarket request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>ArrayList&lt;Integer&gt; "playersFaithPosition": contains all player</li>
 * </ul>
 * @author redrick99
 */
public class EndMarketMessage extends MessageToClient {
    /** All players position onto the faith track is needed to update the clients' View */
    private HashMap<String, Integer> playersFaithPosition;

    /**
     * Constructor for EndMarketMessage Class.
     */
    public EndMarketMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_MARKET;
    }

    /**
     * Getter for "playersFaithPosition" attribute.
     */
    public HashMap<String, Integer> getPlayersFaithPosition() {
        return playersFaithPosition;
    }

    /**
     * Setter for "playersFaithPosition" attribute.
     */
    public void setPlayersFaithPosition(HashMap<String, Integer> playersFaithPosition1) {
        this.playersFaithPosition = playersFaithPosition1;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        if(this.success()) {
            for(Player player : userInteraction.getGame().getPlayers())
                player.setFaithTrackPosition(this.playersFaithPosition.get(player.getNickname()));

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
