package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * EndTurn class defines methods to end a player's turn and go to the next
 * one (if the game is Multiplayer)
 */
public class EndTurn extends Action {
    private int currentPlayer;

    /**
     * Constructor for EndTurn Class.
     */
    public EndTurn() {
        this.actionType = ActionType.END_TURN;
    }

    /**
     * Getter for "actionType" attribute in EndTurn Class.
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
        int idlePlayers = 0;
        this.currentPlayer = game.getCurrentPlayerIndex();
        if(game.getGameType() == GameType.MULTIPLAYER) {
            for(Player player : game.getPlayers())
                if(player.getStatus() == PlayerStatus.IDLE)
                    idlePlayers ++;

            if(idlePlayers == game.getPlayers().size()) {
                this.response = "ALL PLAYERS DISCONNECTED";
                return "ALL PLAYERS DISCONNECTED";
            }
            else {
                game.nextPlayer();
                while(game.getCurrentPlayer().getStatus() == PlayerStatus.IDLE)
                    game.nextPlayer();
            }
        }

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
        EndTurnMessage message = new EndTurnMessage(this.currentPlayer);
        message.setError(this.response);
        message.setNextPlayerId(game.getCurrentPlayerIndex());

        message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
        message.addPossibleAction(ActionType.BUY_CARD);
        message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
        message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        return message;
    }
}
