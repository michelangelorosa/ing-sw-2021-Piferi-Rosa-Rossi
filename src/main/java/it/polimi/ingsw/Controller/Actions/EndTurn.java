package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * EndTurn class defines methods to end a player's turn and go to the next
 * one (if the game is Multiplayer)
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>String "currentPlayer": contains the name of the player who ended his turn</li>
 * </ul>
 * @author redrick99
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
     * Executes the action on the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(!this.canDoAction(actionController))
            return ILLEGAL_ACTION;

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
                actionController.getGame().getCurrentPlayer().clearPossibleActions();
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.BUY_CARD);
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
                actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

                actionController.getGame().nextPlayer();
                while(actionController.getGame().getCurrentPlayer().getStatus() == PlayerStatus.IDLE)
                    actionController.getGame().nextPlayer();
            }
        }

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    /**
     * Prepares a EndTurnMessage MessageToClient type object to be sent to the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        EndTurnMessage message = new EndTurnMessage(this.currentPlayer);
        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

        message.setError(this.response);
        message.setNextPlayerNickname(actionController.getGame().getCurrentPlayerNickname());

        message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
        message.addPossibleAction(ActionType.BUY_CARD);
        message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
        message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        return message;
    }
}
