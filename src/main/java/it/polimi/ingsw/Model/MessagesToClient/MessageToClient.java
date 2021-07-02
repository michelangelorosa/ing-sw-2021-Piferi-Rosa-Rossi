package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MessageToClient Class defines a generic message to be sent to the Client (via the Serializable
 * Java interface).
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>ActionType "actionDone": indicates the Action which created this particular instance</li>
 *     <li>ArrayList&lt;ActionType&gt; "possibleActions": contains new possible actions which the player
 *     can execute after receiving a new MessageToClient (only if the message does not contain
 *     an error statement)</li>
 *     <li>String "error": contains the Controller response after checking if the Action requested
 *     by the player was actually doable</li>
 *     <li>String "playerNickname": contains the name of the player who performed the Action.</li>
 * </ul>
 * @author redrick99
 */
public class MessageToClient implements Serializable {
    protected ActionType actionDone;
    protected ArrayList<ActionType> possibleActions = new ArrayList<>();
    protected String error;
    protected String playerNickname;

    /**
     * Default Constructor for MessageToClient Class.
     */
    public MessageToClient() {
        this.actionDone=ActionType.ACTIVATE_LEADERCARD;
        this.error="SUPERCLASS GENERIC MESSAGE";
    }

    /**
     * Constructor for MessageToClient Class.
     */
    public MessageToClient(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    /**
     * Getter for "playerNickname" attribute.
     */
    public String getPlayerNickname() {
        return playerNickname;
    }

    /**
     * Getter for "actionDone" attribute.
     */
    public ActionType getActionDone() {
        return actionDone;
    }

    /**
     * Getter for "possibleActions" attribute.
     */
    public ArrayList<ActionType> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Getter for "error" attribute.
     */
    public String getError() {
        return error;
    }

    /**
     * Setter for "error" attribute.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Adds one ActionType to "possibleActions" attributes.
     */
    public void addPossibleAction(ActionType type) {
        possibleActions.add(type);
    }

    /**
     * Updates the Client's reduced model so that the player can see changes made to the Model's game.
     * @param userInteraction Class containing the instance of the reduced Game seen by the player.
     * @throws NullPointerException if different conditions are met, depending on the Overridden subclass method.
     */
    public void updateView(UserInteraction userInteraction) throws NullPointerException {
        userInteraction.setPreviousMessage(userInteraction.getMessage());
        userInteraction.setMessage(this);
        this.changePossibleActions(userInteraction);
    }

    /**
     * Finds the Player who performed the Action inside the reduced Game's list of players.
     * @param userInteraction Class containing the instance of the reduced Game seen by the player.
     * @return the Player who performed the action.
     * @throws NullPointerException if there are no player with a nickname equal to this.playerNickname.
     */
    public Player getPlayer(UserInteraction userInteraction) throws NullPointerException{
        for(Player player : userInteraction.getGame().getPlayers())
            if(player.getNickname().equals(this.playerNickname))
                return player;
        throw new NullPointerException("Error: no player found with name \"" + this.playerNickname + "\"" );
    }

    /**
     * Checks if the MessageToClient contains an error statement.
     * @return true if this.error is equal to a success statement.
     */
    protected boolean success() {
        return this.error.equals("SUCCESS");
    }

    /**
     * Checks if the player who received the message was the one playing the current turn.
     * @param userInteraction UserInteraction of the player's View.
     * @return true if the player has to continue his turn.
     */
    public boolean imPlaying(UserInteraction userInteraction) {
        if(this instanceof EndTurnMessage)
            return ((EndTurnMessage) this).getNextPlayerNickname().equals(userInteraction.getGame().getMyNickname());
        else
            return this.playerNickname.equals(userInteraction.getGame().getMyNickname());
    }

    /**
     * Calls a method of the userInteraction to display the Action just done.
     * @param userInteraction Class which contains the User Interface.
     */
    protected void displayAction(UserInteraction userInteraction) {
        userInteraction.nextAction(UIActions.DISPLAY_ACTION);
    }

    /**
     * Calls a method of the userInteraction to display an error given by the Server (through an instance
     * of MessageToClient).
     * @param userInteraction Class which contains the User Interface.
     */
    protected void displayError(UserInteraction userInteraction) {
        userInteraction.nextAction(UIActions.DISPLAY_ERROR);
    }

    /**
     * Sets a new ArrayList of possible actions to the player who just performed the action.
     * @param userInteraction Class containing the instance of the reduced Game seen by the player.
     */
    protected void changePossibleActions(UserInteraction userInteraction) {
        if(this.success() || userInteraction.getGame().getGameType() == GameType.SINGLEPLAYER)
            this.getPlayer(userInteraction).setPossibleActions(this.possibleActions);
    }
}
