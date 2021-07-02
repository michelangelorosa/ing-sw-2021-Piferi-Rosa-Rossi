package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

import java.util.HashMap;

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
    private boolean lastPlayerTurn;

    /**
     * Constructor for EndTurn Class.
     */
    public EndTurn() {
        this.actionType = ActionType.END_TURN;
        this.lastPlayerTurn = false;
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

        actionController.getGame().getCurrentPlayer().clearPossibleActions();
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.BUY_CARD);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.DELETE_LEADERCARD);


        int idlePlayers = 0;
        this.currentPlayer = actionController.getGame().getCurrentPlayerNickname();
        if(actionController.getGame().getGameType() == GameType.MULTIPLAYER) {

            actionController.getGame().checkIfAnyPlayerFinished();

            if(actionController.getGame().isLastTurn() && actionController.getGame().currentPlayerIsLast()) {
                actionController.getGame().setFinalVictoryPoints();
                this.lastPlayerTurn = true;
            }
            else {
                for (Player player : actionController.getGame().getPlayers())
                    if (player.getStatus() == PlayerStatus.IDLE)
                        idlePlayers++;

                if (idlePlayers == actionController.getGame().getPlayers().size()) {
                    this.response = "ALL PLAYERS DISCONNECTED";
                    return "ALL PLAYERS DISCONNECTED";
                } else {

                    actionController.getGame().nextPlayer();
                    while (actionController.getGame().getCurrentPlayer().getStatus() == PlayerStatus.IDLE)
                        actionController.getGame().nextPlayer();
                }
            }
            this.response = "SUCCESS";
        }
        else {
            actionController.getGame().getSinglePlayer().lorenzoTurn(actionController.getGame());
            if(actionController.getGame().getCurrentPlayer().hasFinished()) {
                actionController.getGame().setFinalVictoryPoints();
                actionController.endGamePersistence();
                this.response = "SINGLEPLAYER WIN";
            }
            else if(actionController.getGame().getSinglePlayer().hasLorenzoWon()) {
                actionController.getGame().setFinalVictoryPoints();
                actionController.endGamePersistence();
                this.response = "SINGLEPLAYER LOOSE";
            }
            else
                this.response = "SINGLEPLAYER";
        }
        return "SUCCESS";
    }

    /**
     * Prepares a EndTurnMessage MessageToClient type object to be sent to the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        if (this.response.equals(ILLEGAL_ACTION))
            return illegalAction(new EndTurnMessage(this.currentPlayer), actionController);

        if(this.lastPlayerTurn) {
            actionController.endGamePersistence();
            FinalPointsMessage message = new FinalPointsMessage(this.currentPlayer);

            HashMap<String, Integer> namePoints = new HashMap<>();

            for(Player player : actionController.getGame().getPlayers()) {
                namePoints.put(player.getNickname(), player.getVictoryPoints());
                if (player.getStatus() == PlayerStatus.WON)
                    message.addWinningPlayers(player.getNickname());
            }

            message.setNicknamePoints(namePoints);

            return message;
        }
        else if(this.response.equals("SUCCESS")){
            EndTurnMessage message = new EndTurnMessage(this.currentPlayer);

            message.setError(this.response);
            message.setNextPlayerNickname(actionController.getGame().getCurrentPlayerNickname());

            message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
            message.addPossibleAction(ActionType.BUY_CARD);
            message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
            message.addPossibleAction(ActionType.DELETE_LEADERCARD);

            return message;
        }
        else {
            EndTurnSingleplayerMessage message = new EndTurnSingleplayerMessage(this.currentPlayer);

            message.setError(this.response);
            message.setToken(actionController.getGame().getSinglePlayer().getLastToken());
            message.setVictoryPoints(actionController.getGame().getCurrentPlayer().getVictoryPoints());
            message.setLorenzoFaith(actionController.getGame().getSinglePlayer().getLorenzoFaithPoints());
            message.setTable(actionController.getGame().getDevelopmentCardTable());

            message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
            message.addPossibleAction(ActionType.BUY_CARD);
            message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
            message.addPossibleAction(ActionType.DELETE_LEADERCARD);

            return message;
        }

    }
}
