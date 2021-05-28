package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

import java.util.ArrayList;

/**
 * EndMarket Class contains data and methods to use when a player wants to end a
 * market interaction, transforming Resources into Faith Points.
 */
public class EndMarket extends Action {

    /**
     * Constructor for EndMarket Class.
     */
    public EndMarket() {
        this.actionType = ActionType.END_MARKET;
    }

    /**
     * Getter for "actionType" attribute in EndMarket Class.
     */
    public ActionType getActionType() {
        return this.actionType;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().remainingResourcesToFaith(actionController.getGame().getCurrentPlayer(), actionController.getGame().getPlayers(), actionController.getGame().getFaithTrack());
        actionController.getResetWarehouse().emptyBackupResource();

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        EndMarketMessage message = new EndMarketMessage(actionController.getGame().getCurrentPlayerNickname());
        ArrayList<Integer> faithPositions = new ArrayList<>();

        for(Player player : actionController.getGame().getPlayers())
            faithPositions.add(player.getFaithTrackPosition());

        message.setPlayersFaithPosition(faithPositions);
        message.setError(this.response);
        message.addPossibleAction(ActionType.END_TURN);
        message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        return message;
    }
}