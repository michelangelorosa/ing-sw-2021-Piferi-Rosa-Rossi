package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.ResourceStack;

public class ChoseLeaderCardMessage extends MessageToClient {
    ResourceStack temporaryResources;

    public ChoseLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_LEADER_CARD;
        this.playerId = playerId;
    }

    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
