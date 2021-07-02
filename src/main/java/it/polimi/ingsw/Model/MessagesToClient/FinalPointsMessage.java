package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * FinalPointsMessage Class defines a response message to be sent to a client after
 * the game ended.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>HashMap&lt;String, Integer&gt; "nicknamePoints": contains all players' victory points mapped to their names</li>
 *     <li>ArrayList&lt;String&gt; "winningPlayers": contains the names of the players who won</li>
 * </ul>
 * @author redrick99
 */
public class FinalPointsMessage extends MessageToClient {
    private HashMap<String, Integer> nicknamePoints;
    private final ArrayList<String> winningPlayers = new ArrayList<>();

    /**
     * Constructor for FinalPointsMessage.
     */
    public FinalPointsMessage(String playerNickname) {
        super(playerNickname);
        this.error = "SUCCESS";
    }

    /**
     * Getter for "nicknamePoints" attribute.
     */
    public HashMap<String, Integer> getNicknamePoints() {
        return nicknamePoints;
    }

    /**
     * Setter for "nicknamePoints" attribute.
     */
    public void setNicknamePoints(HashMap<String, Integer> nicknamePoints) {
        this.nicknamePoints = nicknamePoints;
    }

    /**
     * Getter for "winningPlayers" attribute.
     */
    public ArrayList<String> getWinningPlayers() {
        return winningPlayers;
    }

    /**
     * Adds a name to the ArrayList of winning players' names.
     * @param winningPlayer name of the player to be added.
     */
    public void addWinningPlayers(String winningPlayer) {
        this.winningPlayers.add(winningPlayer);
    }

    /**
     * Updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        for(Player player : userInteraction.getGame().getPlayers()) {
            player.setVictoryPoints(this.nicknamePoints.get(player.getNickname()));
            if(this.winningPlayers.contains(player.getNickname()))
                player.setStatus(PlayerStatus.WON);
            else
                player.setStatus(PlayerStatus.LOST);
        }

        userInteraction.nextAction(UIActions.FINAL_POINTS);
    }
}
