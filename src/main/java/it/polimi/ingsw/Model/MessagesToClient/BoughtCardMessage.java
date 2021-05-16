package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class BoughtCardMessage extends MessageToClient {
    private it.polimi.ingsw.View.ReducedModel.Warehouse warehouse;
    private it.polimi.ingsw.View.ReducedModel.Strongbox strongbox;

    public BoughtCardMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_CARD;
        this.playerId = playerId;
    }

    public void setWarehouse(it.polimi.ingsw.View.ReducedModel.Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(it.polimi.ingsw.View.ReducedModel.Strongbox strongbox) {
        this.strongbox = strongbox;
    }
}
