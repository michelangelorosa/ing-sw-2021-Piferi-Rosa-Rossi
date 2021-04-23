package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class EndProductionMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    public EndProductionMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_PRODUCTION;
        this.playerId = playerId;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }
}
