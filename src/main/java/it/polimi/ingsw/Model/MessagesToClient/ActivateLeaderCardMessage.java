package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;

public class ActivateLeaderCardMessage extends MessageToClient {
    private int leaderCardPosition;

    public ActivateLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.ACTIVATE_LEADERCARD;
        this.playerId = playerId;
    }

    public void setLeaderCardPosition(int leaderCardPosition) {
        this.leaderCardPosition = leaderCardPosition;
    }
}
