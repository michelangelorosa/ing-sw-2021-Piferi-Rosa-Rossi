package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;

public class ActivateProductionMessage extends MessageToClient {

    public ActivateProductionMessage(int playerId) {
        this.actionDone = ActionType.ACTIVATE_PRODUCTION;
        this.playerId = playerId;
    }
}
