package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

public class PaymentMessage extends MessageToClient {
    /**Payment message need a warehouse a strongbox and a temporary resource*/
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;
    private RedResourceStack temporaryResources;

    /**Constructor for PaymentMessage*/
    public PaymentMessage(String playerNickname) {
        super(playerNickname);
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
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("HasToPay")) {
            Game game = userInteraction.getGame();
            for(Player player : game.getPlayers()) {
                player.setWarehouse(this.warehouse);
                player.setStrongbox(this.strongbox);
                player.setTemporaryResources(this.temporaryResources);
                player.setPossibleActions(this.possibleActions);
            }
        }
        else {
            userInteraction.getUi().displayError(this.error);
        }
    }
}
