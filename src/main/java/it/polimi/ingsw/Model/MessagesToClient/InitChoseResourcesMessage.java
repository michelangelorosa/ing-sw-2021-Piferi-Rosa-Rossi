package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;

public class InitChoseResourcesMessage extends MessageToClient {
    private RedWarehouse warehouse;

    public InitChoseResourcesMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.INIT_CHOOSE_RESOURCES;
    }

    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void updateView(UserInteraction userInteraction) {
        if(!this.error.equals("SUCCESS")) {
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers()) {
                if (player.getNickname().equals(this.playerNickname)) {
                    player.setPossibleActions(this.possibleActions);
                    player.setWarehouse(this.warehouse);
                    //TODO display changes
                }
            }
        }
        else {
            userInteraction.getUi().displayError(this.error);
        }
    }
}
