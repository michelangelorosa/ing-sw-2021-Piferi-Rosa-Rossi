package it.polimi.ingsw.Controller.Actions.Cheats;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Server.Server;

public class VictoryPointsCheat extends Action {
    private final int victoryPoints = 50;

    public VictoryPointsCheat() {
        this.actionType = ActionType.VICTORYPOINTS_CHEAT;
    }

    @Override
    public String doAction(ActionController actionController) {
        if(Server.isCheatMode()) {
            actionController.getGame().getCurrentPlayer().addVictoryPoints(50);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
        else
            this.response = "{ ! CHEAT MODE NOT ACTIVATED ! }";
        return "{ ! CHEAT MODE NOT ACTIVATED ! }";
    }
}
