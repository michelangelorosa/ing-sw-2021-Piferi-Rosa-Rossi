package it.polimi.ingsw.Controller.Actions.Cheats;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Server.Server;

/**
 * LeaderCardsCheat Class is a special Action to be used when Cheat Mode is enabled to activate all of the player's leaderCards.
 */
public class LeaderCardsCheat extends Action {

    /**
     * Constructor for LeaderCardsCheatClass.
     */
    public LeaderCardsCheat() {
        this.actionType = ActionType.LEADERCARDS_CHEAT;
    }

    /**
     * Performs the specified cheat Action on the Server's Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if Cheat Mode is enabled on the Server.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(Server.isCheatMode()) {
            actionController.getGame().getCurrentPlayer().getBoard().activateLeaderCard(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0]);
            actionController.getGame().getCurrentPlayer().getBoard().activateLeaderCard(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1]);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
        else
            this.response = "{ ! CHEAT MODE NOT ACTIVATED ! }";
        return "{ ! CHEAT MODE NOT ACTIVATED ! }";
    }

    /**
     * Prepares a generic CheatMessage to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return a CheatMessage.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        return this.cheatMessage(actionController);
    }
}
