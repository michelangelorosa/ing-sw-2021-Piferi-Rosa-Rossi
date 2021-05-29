package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * EndTurn class defines methods to end a player's turn and go to the next
 * one (if the game is Multiplayer)
 */
public class EndTurn extends Action {
    private String currentPlayer;

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
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        int idlePlayers = 0;
        this.currentPlayer = actionController.getGame().getCurrentPlayerNickname();
        if(actionController.getGame().getGameType() == GameType.MULTIPLAYER) {
            for(Player player : actionController.getGame().getPlayers())
                if(player.getStatus() == PlayerStatus.IDLE)
                    idlePlayers ++;

            if(idlePlayers == actionController.getGame().getPlayers().size()) {
                this.response = "ALL PLAYERS DISCONNECTED";
                return "ALL PLAYERS DISCONNECTED";
            }
            else {
                actionController.getGame().nextPlayer();
                while(actionController.getGame().getCurrentPlayer().getStatus() == PlayerStatus.IDLE)
                    actionController.getGame().nextPlayer();
            }
        }

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        EndTurnMessage message = new EndTurnMessage(this.currentPlayer);
        message.setError(this.response);
        message.setNextPlayerNickname(actionController.getGame().getCurrentPlayerNickname());

        message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
        message.addPossibleAction(ActionType.BUY_CARD);
        message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
        message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        return message;
    }
}
