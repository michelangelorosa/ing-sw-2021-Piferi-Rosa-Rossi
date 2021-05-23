package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;

public class PaymentMessage extends MessageToClient {
    /**Payment message need a warehouse a strongbox and a temporary resource*/
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;
    private RedResourceStack temporaryResources;

    /**Constructor for PaymentMessage*/
    public PaymentMessage(int playerId) {
        this.playerId = playerId;
    }

    /**Setter for action done*/
    public void setActionDone(ActionType actionType) {
        this.actionDone = actionType;
    }

    /**Setter for warehouse*/
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**Setter for TemporaryResources*/
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("HasToPay")) {
            for(Player player : game.getPlayers()) {
                player.setWarehouse(this.warehouse);
                player.setStrongbox(this.strongbox);
                player.setTemporaryResources(this.temporaryResources);
                player.setPossibleActions(this.possibleActions);
            }
        }
    }
}
