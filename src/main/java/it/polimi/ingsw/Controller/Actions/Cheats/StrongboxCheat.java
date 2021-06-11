package it.polimi.ingsw.Controller.Actions.Cheats;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Server.Server;

public class StrongboxCheat extends Action {
    private final ResourceStack strongboxResources = new ResourceStack(100, 100, 100, 100);

    public StrongboxCheat() {
        this.actionType = ActionType.STRONGBOX_CHEAT;
    }

    @Override
    public String doAction(ActionController actionController) {
        if(Server.isCheatMode()) {
            actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addProductionResources(strongboxResources);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
        else
            this.response = "{ ! CHEAT MODE NOT ACTIVATED ! }";
        return "{ ! CHEAT MODE NOT ACTIVATED ! }";
    }

    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        return this.cheatMessage(actionController);
    }
}
