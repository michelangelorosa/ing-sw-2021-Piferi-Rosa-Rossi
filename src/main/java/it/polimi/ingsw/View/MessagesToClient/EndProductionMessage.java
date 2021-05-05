package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.*;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.Controller.Actions.ActionType;

public class EndProductionMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    public EndProductionMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_PRODUCTION;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        for(Player player : game.getPlayers())
            if(player.getTurnPosition() == this.playerId) {
                player.setWarehouse(this.warehouse);
                player.setStrongbox(this.strongbox);
                player.setPossibleActions(this.possibleActions);
            }
    }
}
