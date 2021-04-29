package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MessageToClient Class defines a generic message to be sent to the client (via the Serializable
 * Java interface).
 */
public class MessageToClient implements Serializable {
    /** actionDone defines the action requested by the client */
    protected ActionType actionDone;
    /**
     * possibleActions contains all possible actions a player could request after
     * the action he just requested.
     */
    protected ArrayList<ActionType> possibleActions = new ArrayList<>();
    /** error attribute specifies the message error (or SUCCESS) to be displayed client side */
    protected String error;
    /** playerId indicates the current player doing the actions */
    protected int playerId;

    /**
     * Default Constructor for MessageToClient Class.
     */
    public MessageToClient() {

    }

    /**
     * Getter for "actionDone" attribute in MessageToClient Class.
     */
    public ActionType getActionDone() {
        return actionDone;
    }

    /**
     * Getter for "possibleActions" attribute in MessageToClient Class.
     */
    public ArrayList<ActionType> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Getter for "playerId" attribute in MessageToClient Class.
     */
    public int getPlayerPosition() {
        return playerId;
    }

    /**
     * Getter for "error" attribute in MessageToClient Class.
     */
    public String getError() {
        return error;
    }

    /**
     * Setter for "error" attribute in MessageToClient Class.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Setter used to add one Resource Type to "possibleActions" attributes.
     */
    public void addPossibleAction(ActionType type) {
        possibleActions.add(type);
    }
}