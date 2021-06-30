package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.PopeTileClass;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.HashMap;

/**
 * DiscardLeaderMessage Class defines a response message to be sent to a client after
 * a DiscardLeader Action request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>int "number": position of the card which the player wanted to discard</li>
 *     <li>RedLeaderCard "leaderCard": Reduced Leader Card which the player has discarded</li>
 *     <li>int "faithPoints": faith points of the player who discarded the Leader Card</li>
 *     <li>HashMap&lt;String, PopeTileClass[]&gt; "playersPopeTiles": contains all players' Pope Tiles mapped to the players' names</li>
 * </ul>
 * @author francescopiferi99
 */
public class DiscardLeaderCardMessage extends MessageToClient{
    private int number;
    private RedLeaderCard leaderCard;
    private int faithPoints;
    private HashMap<String, PopeTileClass[]> playersPopeTiles;

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

    public int getFaithPoints() {
        return faithPoints;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

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
            player.getLeaderCards()[this.number] = leaderCard;
            player.setFaithTrackPosition(this.faithPoints);
            for(Player p : userInteraction.getGame().getPlayers())
                p.setPopeTiles(playersPopeTiles.get(p.getNickname()));

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
