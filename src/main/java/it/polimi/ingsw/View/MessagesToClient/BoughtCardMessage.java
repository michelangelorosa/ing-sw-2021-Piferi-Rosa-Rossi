package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;

public class BoughtCardMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    public BoughtCardMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_CARD;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS")) {
            for (Player player : game.getPlayers())
                if (player.getTurnPosition() == this.playerId)
                    player.setPossibleActions(this.possibleActions);
        }

        else {
            //TODO error message
        }
    }
}
