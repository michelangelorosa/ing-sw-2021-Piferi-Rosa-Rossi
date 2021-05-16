package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.*;

/**
 * ChooseProductionOutputMessage Class defines a response message to be sent to the client
 * after a ChooseProductionOutput request.
 */
public class ChoseProductionOutputMessage extends MessageToClient {
    /** The player's strongbox is needed to update the client's View */
    private it.polimi.ingsw.View.ReducedModel.Strongbox strongbox;

    /**
     * Constructor for ChooseProductionOutputMessage Class.
     */
    public ChoseProductionOutputMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_PRODUCTION_OUTPUT;
        this.playerId = playerId;
    }

    /**
     * Setter for "strongbox" attribute in ChooseProductionOutputMessage Class.
     */
    public void setStrongbox(it.polimi.ingsw.View.ReducedModel.Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Getter for "strongbox" attribute in ChooseProductionOutputMessage Class.
     */
    public it.polimi.ingsw.View.ReducedModel.Strongbox getStrongbox() {
        return strongbox;
    }
}
