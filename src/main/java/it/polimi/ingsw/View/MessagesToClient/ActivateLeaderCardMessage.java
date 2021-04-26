package it.polimi.ingsw.View.MessagesToClient;

public class ActivateLeaderCardMessage extends MessageToClient {
    private int leaderCardPosition;

    public ActivateLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.ACTIVATE_LEADERCARD;
        this.playerId = playerId;
    }
}
