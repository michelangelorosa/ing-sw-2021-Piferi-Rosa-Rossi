package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.*;
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
