package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Warehouse;
import it.polimi.ingsw.View.ReducedModel.Strongbox;

public class BoughtCardMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    public BoughtCardMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_CARD;
        this.playerId = playerId;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Strongbox getStrongbox() {
        return strongbox;
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
