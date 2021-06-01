package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.ArrayList;

/**
 * EndMarketMessage Class defines a response message to be sent to the client after
 * a EndMarket request.
 */
public class EndMarketMessage extends MessageToClient {
    /** All players position onto the faith track is needed to update the clients' View */
    private ArrayList<Integer> playersFaithPosition;

    /**
     * Constructor for EndMarketMessage Class.
     */
    public EndMarketMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_MARKET;
    }

    /**
     * Setter for "playersFaithPosition" attribute in EndMarketMessageClass.
     */
    public void setPlayersFaithPosition(ArrayList<Integer> playersFaithPosition) {
        this.playersFaithPosition = playersFaithPosition;
    }

    /**
     * Getter for "playersFaithPosition" attribute in EndMarketMessageClass.
     */
    public ArrayList<Integer> getPlayersFaithPosition() {
        return playersFaithPosition;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        Game game = userInteraction.getGame();
        for(int i = 0; i < game.getPlayers().size(); i++) {
            game.getPlayers().get(i).setFaithTrackPosition(this.playersFaithPosition.get(i));
            if(game.getPlayers().get(i).getNickname().equals(this.playerNickname))
                game.getPlayers().get(i).setPossibleActions(this.possibleActions);
        }
    }
}
