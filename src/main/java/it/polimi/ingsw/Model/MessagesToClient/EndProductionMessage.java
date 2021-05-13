package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class EndProductionMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    /**Constructor for EndProductionMessage*/
    public EndProductionMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_PRODUCTION;
        this.playerId = playerId;
    }

    /**Setter for Warehouse*/
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }
}
