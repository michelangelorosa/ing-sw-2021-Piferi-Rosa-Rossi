package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.Warehouse;
import it.polimi.ingsw.View.ReducedModel.Strongbox;

public class EndProductionMessage extends MessageToClient {
    private Warehouse warehouse;
    private Strongbox strongbox;

    /**Constructor for EndProductionMessage*/
    public EndProductionMessage(int playerId) {
        this.actionDone = ActionType.END_PAY_PRODUCTION;
        this.playerId = playerId;
    }

    /**Setter for Warehouse*/
    public void setWarehouse(it.polimi.ingsw.View.ReducedModel.Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(it.polimi.ingsw.View.ReducedModel.Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        for(Player player : game.getPlayers())
            if(player.getTurnPosition() == this.playerId) {
                player.setWarehouse(this.warehouse);
                player.setStrongbox(this.strongbox);
                player.setPossibleActions(this.possibleActions);
            }
    }
}
