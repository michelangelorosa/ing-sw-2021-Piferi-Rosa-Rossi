package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class EndProductionMessage extends MessageToClient {
    private it.polimi.ingsw.View.ReducedModel.Warehouse warehouse;
    private it.polimi.ingsw.View.ReducedModel.Strongbox strongbox;

    /**Constructor for EndProductionMessage*/
    public EndProductionMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_PRODUCTION;
        this.playerId = playerId;
    }

    /**Setter for Warehouse*/
    public void setWarehouse(it.polimi.ingsw.View.ReducedModel.Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(it.polimi.ingsw.View.ReducedModel.Strongbox strongbox) {
        this.strongbox = strongbox;
    }
}
