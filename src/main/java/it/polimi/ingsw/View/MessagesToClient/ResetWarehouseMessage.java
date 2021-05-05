package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.*;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.Controller.Actions.ActionType;

public class ResetWarehouseMessage extends MessageToClient {
    private Warehouse warehouse;
    private ResourceStack temporaryResources;

    /**
     * Constructor for ResetWarehouseMessage Class.
     */
    public ResetWarehouseMessage(int playerId) {
        this.actionDone = ActionType.RESET_WAREHOUSE;
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
                player.setTemporaryResources(this.temporaryResources);
                player.setPossibleActions(this.possibleActions);
            }
    }
}
