package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Model.ResourceStack;

public class ChoseLeaderCardMessage extends MessageToClient {
    ResourceStack temporaryResources;

    public ChoseLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_LEADER_CARD;
        this.playerId = playerId;
    }
}
