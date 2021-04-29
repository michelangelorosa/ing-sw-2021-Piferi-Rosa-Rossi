package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.Strongbox;
import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

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

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {

    }
}
