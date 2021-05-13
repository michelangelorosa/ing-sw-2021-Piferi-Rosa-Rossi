package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.ResourceStack;

public class ChoseLeaderCardMessage extends MessageToClient {
    /**ChoseLeaderCardMessage need a temporary resource stack*/
    ResourceStack temporaryResources;

    /**ChoseLeaderCardMessage's constructor*/
    public ChoseLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_LEADER_CARD;
        this.playerId = playerId;
    }

    /**Setter for Temporary resource*/
    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
