package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;

public class BoughtCardMessage extends MessageToClient {
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;

    public BoughtCardMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_CARD;
        this.playerId = playerId;
    }

    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    public RedStrongbox getStrongbox() {
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
