package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class PaymentMessage extends MessageToClient {
    /**Payment message need a warehuse a strongbox and a temporary resource*/
    private Warehouse warehouse;
    private Strongbox strongbox;
    private ResourceStack temporaryResources;

    /**Constructor for PaymentMessage*/
    public PaymentMessage(int playerId) {
        this.playerId = playerId;
    }

    /**Setter for action done*/
    public void setActionDone(ActionType actionType) {
        this.actionDone = actionType;
    }

    /**Setter for warehouse*/
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**Setter for TemporaryResources*/
    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
