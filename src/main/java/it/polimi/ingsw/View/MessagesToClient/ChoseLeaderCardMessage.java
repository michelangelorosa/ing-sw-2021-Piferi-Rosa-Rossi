package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

public class ChoseLeaderCardMessage extends MessageToClient {
    ResourceStack temporaryResources;

    public ChoseLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_LEADER_CARD;
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
