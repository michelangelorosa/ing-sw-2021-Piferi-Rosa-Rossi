package it.polimi.ingsw.Controller.Actions;

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
     * Method written because of interface implementation.
     */
    @Override
    public boolean isCorrect() {
        return true;
    }

    /**
     * Method written because of interface implementation.
     */
    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        game.getCurrentPlayer().getBoard().getResourceManager().remainingResourcesToFaith(game.getCurrentPlayer(), game.getPlayers(), game.getFaithTrack());
        resetWarehouse.emptyBackupResource();

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @param game Current instance of the Game being played.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(Game game) {
        EndMarketMessage message = new EndMarketMessage(game.getCurrentPlayerIndex());
        ArrayList<Integer> faithPositions = new ArrayList<>();

        for(Player player : game.getPlayers())
            faithPositions.add(player.getFaithTrackPosition());

        message.setPlayersFaithPosition(faithPositions);
        message.setError(this.response);
        message.addPossibleAction(ActionType.END_TURN);
        message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        return message;
    }
}