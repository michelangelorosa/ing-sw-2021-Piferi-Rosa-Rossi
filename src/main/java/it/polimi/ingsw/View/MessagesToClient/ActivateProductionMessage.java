package it.polimi.ingsw.View.MessagesToClient;

public class ActivateProductionMessage extends MessageToClient {

    public ActivateProductionMessage(int playerId) {
        this.actionDone = ActionType.ACTIVATE_PRODUCTION;
        this.playerId = playerId;
    }
}
