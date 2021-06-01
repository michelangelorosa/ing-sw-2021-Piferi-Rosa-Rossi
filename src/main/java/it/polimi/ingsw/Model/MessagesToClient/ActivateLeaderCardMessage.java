package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;

public class ActivateLeaderCardMessage extends MessageToClient {
    private int leaderCardPosition;
    private RedLeaderCard leaderCard;

    public ActivateLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ACTIVATE_LEADERCARD;
    }

    public int getLeaderCardPosition() {
        return leaderCardPosition;
    }

    public void setLeaderCardPosition(int leaderCardPosition) {
        this.leaderCardPosition = leaderCardPosition;
    }

    public RedLeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(RedLeaderCard leaderCard) {
        this.leaderCard = leaderCard;
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
                    player.getLeaderCards()[leaderCardPosition] = leaderCard;
                    player.setPossibleActions(this.possibleActions);
                }
        }
        else {
            userInteraction.getUi().displayError(this.error);
        }
    }
}
