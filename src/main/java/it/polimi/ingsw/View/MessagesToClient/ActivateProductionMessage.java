package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;

public class ActivateProductionMessage extends MessageToClient {

    public ActivateProductionMessage(int playerId) {
        this.actionDone = ActionType.ACTIVATE_PRODUCTION;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS") || this.error.equals("No Payment"))
            for(Player player : game.getPlayers())
                if(player.getTurnPosition() == this.playerId)
                    player.setPossibleActions(this.possibleActions);
        else{
           //TODO error message
        }
    }
}
