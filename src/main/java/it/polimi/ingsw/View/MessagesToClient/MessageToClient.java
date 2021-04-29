package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.View.ReducedModel.Enums.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void updateView(Game game) {

    }
}
