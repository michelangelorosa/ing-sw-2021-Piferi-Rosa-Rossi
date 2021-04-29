package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

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

    }
}
