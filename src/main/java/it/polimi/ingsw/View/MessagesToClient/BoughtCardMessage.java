package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.Strongbox;
import it.polimi.ingsw.Model.Warehouse;

public class BoughtCardMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    public BoughtCardMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_CARD;
        this.playerId = playerId;
    }
}
