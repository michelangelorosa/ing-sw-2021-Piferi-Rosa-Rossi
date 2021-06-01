package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

public class EndProductionMessage extends MessageToClient {
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;

    /**Constructor for EndProductionMessage*/
    public EndProductionMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_PAY_PRODUCTION;
    }

    /**Setter for Warehouse*/
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**Setter for Strongbox*/
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        Game game = userInteraction.getGame();
        for(Player player : game.getPlayers())
            if(player.getNickname().equals(this.playerNickname)) {
                player.setWarehouse(this.warehouse);
                player.setStrongbox(this.strongbox);
                player.setPossibleActions(this.possibleActions);
            }
    }
}
