package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.PopeTileClass;
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
 *     <li>ArrayList&lt;Integer&gt; "playersFaithPosition": contains all players positions inside the Faith Track</li>
 *     <li>HashMap&lt;String, PopeTileClass[]&gt; "playersPopeTiles": contains all players' Pope Tiles mapped to the players' names</li>
 * </ul>
 * @author redrick99
 */
public class EndMarketMessage extends MessageToClient {
    private HashMap<String, Integer> playersFaithPosition;
    private HashMap<String, PopeTileClass[]> playersPopeTiles;

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
     * Getter for "playersPopeTiles" attribute.
     */
    public void setPlayersPopeTiles(HashMap<String, PopeTileClass[]> playersPopeTiles) {
        this.playersPopeTiles = playersPopeTiles;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        if(this.success()) {
            for(Player player : userInteraction.getGame().getPlayers()) {
                player.setFaithTrackPosition(this.playersFaithPosition.get(player.getNickname()));
                player.setPopeTiles(this.playersPopeTiles.get(player.getNickname()));
            }

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
