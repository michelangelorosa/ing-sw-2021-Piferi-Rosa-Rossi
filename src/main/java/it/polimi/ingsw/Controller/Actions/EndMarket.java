package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EndMarket Class contains data and methods to use when a player wants to end a
 * market interaction, transforming Resources into Faith Points.
 * @author redrick99
 */
public class EndMarket extends Action {

    /**
     * Constructor for EndMarket Class.
     */
    public EndMarket() {
        this.actionType = ActionType.END_MARKET;
    }

    /**
     * Executes the action on the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS", since a EndMarket action cannot encounter any error.
     */
    @Override
    public String doAction(ActionController actionController) throws ModelException {
            if (!this.canDoAction(actionController))
                return ILLEGAL_ACTION;

            actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().remainingResourcesToFaith(actionController.getGame().getCurrentPlayer(), actionController.getGame().getPlayers(), actionController.getGame().getFaithTrack());
            actionController.getResetWarehouse().emptyBackupResource();

            this.response = "SUCCESS";
            actionController.getGame().getCurrentPlayer().clearPossibleActions();
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
            return "SUCCESS";

    }

    /**
     * Prepares a EndMarket MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        EndMarketMessage message = new EndMarketMessage(actionController.getGame().getCurrentPlayerNickname());

        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

        HashMap<String, Integer> playersFaithPosition = new HashMap<>();

        for(Player player : actionController.getGame().getPlayers())
            playersFaithPosition.put(player.getNickname(), player.getFaithTrackPosition());

        message.setPlayersFaithPosition(playersFaithPosition);
        message.setError(this.response);
        message.addPossibleAction(ActionType.END_TURN);

        if(actionController.getGame().getCurrentPlayer().canDo(ActionType.ACTIVATE_LEADERCARD))
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        return message;
    }
}