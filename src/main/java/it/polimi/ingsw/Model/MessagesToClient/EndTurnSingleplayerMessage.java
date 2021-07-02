package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardTable;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * EndTurnSingleplayerMessage Class defines a response message to be sent to a client after
 * an EndTurn request when playing a Singleplayer game.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>SoloActionToken "token": token drawn by Lorenzo il Magnifico</li>
 *     <li>int "victoryPoints": player's victory points</li>
 *     <li>int "lorenzoFaith": position of Lorenzo il Magnifico inside the Faith Track</li>
 *     <li>DevelopmentCardTable "table": table of Development Cards from the Model's game to be set in the Client's View</li>
 * </ul>
 * @author redrick99
 */
public class EndTurnSingleplayerMessage extends MessageToClient{
    private SoloActionToken token;
    private int victoryPoints;
    private int lorenzoFaith;
    private DevelopmentCardTable table;

    /**
     * Constructor for EndTurnSingleplayerMessage Class.
     */
    public EndTurnSingleplayerMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_TURN_SINGLEPLAYER;
    }

    /**
     * Getter for "token" attribute.
     */
    public SoloActionToken getToken() {
        return token;
    }

    /**
     * Setter for "token" attribute.
     */
    public void setToken(SoloActionToken token) {
        this.token = token;
    }

    /**
     * Getter for "victoryPoints" attribute.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Setter for "victoryPoints" attribute.
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Getter for "lorenzoFaith" attribute.
     */
    public int getLorenzoFaith() {
        return lorenzoFaith;
    }

    /**
     * Setter for "lorenzoFaith" attribute.
     */
    public void setLorenzoFaith(int lorenzoFaith) {
        this.lorenzoFaith = lorenzoFaith;
    }

    /**
     * Getter for "table" attribute.
     */
    public DevelopmentCardTable getTable() {
        return table;
    }

    /**
     * Setter for "table" attribute.
     */
    public void setTable(DevelopmentCardTable table) {
        this.table = table;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        switch (this.error) {
            case "SINGLEPLAYER":
                userInteraction.getGame().setDevelopmentCardTable(this.table);
                userInteraction.setLorenzoToken(this.token);
                userInteraction.getGame().getPlayers().get(1).setFaithTrackPosition(this.lorenzoFaith);
                userInteraction.nextAction(UIActions.SINGLEPLAYER_TURN);
                break;
            case "SINGLEPLAYER LOOSE":
                userInteraction.getGame().getPlayers().get(0).setVictoryPoints(this.victoryPoints);
                userInteraction.nextAction(UIActions.SINGLEPLAYER_END_LOST);
                break;
            case "SINGLEPLAYER WIN":
                userInteraction.getGame().getPlayers().get(0).setVictoryPoints(this.victoryPoints);
                userInteraction.nextAction(UIActions.SINGLEPLAYER_END_WON);
                break;
        }
    }
}
