package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.Strongbox;

public class ChoseProductionOutputMessage extends MessageToClient {
    /** The player's strongbox is needed to update the client's View */
    private Strongbox strongbox;

    /**
     * Constructor for ChooseProductionOutputMessage Class.
     */
    public ChoseProductionOutputMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_PRODUCTION_OUTPUT;
        this.playerId = playerId;
    }
}
