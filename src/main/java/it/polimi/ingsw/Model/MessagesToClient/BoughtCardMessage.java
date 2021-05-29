package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;

public class BoughtCardMessage extends MessageToClient {
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;

    public BoughtCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_PAY_CARD;
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
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("SUCCESS")) {
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers())
                if (player.getNickname().equals(this.getPlayerNickname()))
                    player.setPossibleActions(this.possibleActions);
        }
        else {
            userInteraction.getUi().displayError(this.error);
        }
    }
}
