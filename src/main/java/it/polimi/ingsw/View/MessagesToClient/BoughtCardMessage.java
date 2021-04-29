package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.Strongbox;
import it.polimi.ingsw.Model.Warehouse;
import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

public class BoughtCardMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    public BoughtCardMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_CARD;
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
