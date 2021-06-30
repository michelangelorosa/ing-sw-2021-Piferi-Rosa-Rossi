package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.PopeTileClass;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.HashMap;

/**
 * ChooseProductionOutputMessage Class defines a response message to be sent to the client
 * after a ChooseProductionOutput request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedStrongbox "strongbox": the new Strongbox to be set to the corresponding player in the
 *     Client's View</li>
 *     <li>int "faithPoints": faith points of the player who finished a production</li>
 *     <li>HashMap&lt;String, PopeTileClass[]&gt; "playersPopeTiles": contains all players' Pope Tiles mapped to the players' names</li>
 * </ul>
 * @author redrick99
 */
public class ChoseProductionOutputMessage extends MessageToClient {
    private RedStrongbox strongbox;
    private int faithPoints;
    private HashMap<String, PopeTileClass[]> playersPopeTiles;

    /**
     * Constructor for ChooseProductionOutputMessage Class.
     */
    public ChoseProductionOutputMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_PRODUCTION_OUTPUT;
    }

    /**
     * Setter for "strongbox" attribute.
     */
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Getter for "strongbox" attribute.
     */
    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    /**
     * Getter for "faithPoints" attribute.
     */
    public int getFaithPoints() {
        return faithPoints;
    }

    /**
     * Setter for "faithPoints" attribute.
     */
    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    /**
     * Setter for "playersPopeTiles" attribute.
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

        Player player = this.getPlayer(userInteraction);

        if(this.success()) {
            player.setStrongbox(this.strongbox);
            player.setFaithTrackPosition(this.faithPoints);
            for(Player p : userInteraction.getGame().getPlayers())
                p.setPopeTiles(this.playersPopeTiles.get(p.getNickname()));

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
