package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UserInteraction;

public class DiscardLeaderCardMessage extends MessageToClient{
    private int number;

    public DiscardLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.DELETE_LEADERCARD;
    }

    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("SUCCESS")) {
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers()) {
                if (player.getNickname().equals(this.getPlayerNickname()))
                    player.setPossibleActions(this.possibleActions);
            }

        }

        else {
            userInteraction.getUi().displayError(this.error);
        }
    }
}
