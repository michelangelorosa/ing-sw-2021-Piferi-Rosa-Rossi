package it.polimi.ingsw.Controller.Actions.Cheats;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Server.Server;

public class LeaderCardsCheat extends Action {

    public LeaderCardsCheat() {
        this.actionType = ActionType.LEADERCARDS_CHEAT;
    }

    @Override
    public String doAction(ActionController actionController) {
        if(Server.isCheatMode()) {
            actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].setActive(true);
            actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].setActive(true);
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
