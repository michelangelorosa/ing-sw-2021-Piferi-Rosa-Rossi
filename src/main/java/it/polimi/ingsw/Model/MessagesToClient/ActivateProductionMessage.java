package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;

public class ActivateProductionMessage extends MessageToClient {

    public ActivateProductionMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ACTIVATE_PRODUCTION;
    }


    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */

    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("SUCCESS") || this.error.equals("No Payment")) {
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers())
                if (player.getNickname().equals(this.getPlayerNickname()))
                    player.setPossibleActions(this.possibleActions);
        }
        else {
            userInteraction.getUi().displayError(this.error);
        }
    }

}
