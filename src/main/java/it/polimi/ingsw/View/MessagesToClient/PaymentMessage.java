package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.Strongbox;
import it.polimi.ingsw.Model.Warehouse;
import it.polimi.ingsw.View.ReducedModel.Game;

public class PaymentMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;
    private ResourceStack temporaryResources;

    public PaymentMessage(int playerId) {
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
