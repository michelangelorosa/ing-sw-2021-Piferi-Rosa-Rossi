package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

public class PaymentMessage extends MessageToClient {
    /**Payment message need a warehuse a strongbox and a temporary resource*/
    private it.polimi.ingsw.View.ReducedModel.Warehouse warehouse;
    private it.polimi.ingsw.View.ReducedModel.Strongbox strongbox;
    private it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources;

    /**Constructor for PaymentMessage*/
    public PaymentMessage(int playerId) {
        this.playerId = playerId;
    }

    /**Setter for action done*/
    public void setActionDone(ActionType actionType) {
        this.actionDone = actionType;
    }

    /**Setter for warehouse*/
    public void setWarehouse(it.polimi.ingsw.View.ReducedModel.Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(it.polimi.ingsw.View.ReducedModel.Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**Setter for TemporaryResources*/
    public void setTemporaryResources(it.polimi.ingsw.View.ReducedModel.ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
