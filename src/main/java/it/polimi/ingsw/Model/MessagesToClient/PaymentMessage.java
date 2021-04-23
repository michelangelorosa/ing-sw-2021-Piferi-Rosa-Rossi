package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class PaymentMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;
    private ResourceStack temporaryResources;

    public PaymentMessage(int playerId) {
        this.playerId = playerId;
    }

    public void setActionDone(ActionType actionType) {
        this.actionDone = actionType;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
