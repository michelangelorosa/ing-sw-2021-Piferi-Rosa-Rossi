package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.LeaderCard;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;

public class ActivateLeaderCardMessage extends MessageToClient {
    private int leaderCardPosition;

    public ActivateLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ACTIVATE_LEADERCARD;
    }

    public void setLeaderCardPosition(int leaderCardPosition) {
        this.leaderCardPosition = leaderCardPosition;
    }


    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("SUCCESS")) {
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers())
                if (player.getNickname().equals(this.getPlayerNickname())) {
                    ((LeaderCard) player.getLeaderCards()[this.leaderCardPosition]).setActive(true);
                    player.setPossibleActions(this.possibleActions);
                }
        }
                else {
                    //TODO error message
                }
    }
}
