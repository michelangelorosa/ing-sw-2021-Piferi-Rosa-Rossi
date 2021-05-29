package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.*;

import java.io.Serializable;

/**
 * Action abstract Class defines common Data and Methods to perform an action on the model after a Client's request.
 */
public class Action implements Serializable {
    protected ActionType actionType;
    protected String response = null;

    public String getResponse() {
        return response;
    }

    /**
     * Checks if the Action subclass parameters are correct and can be used by the Model's Classes to perform
     * the given action.
     * @return true if parameters are correct.
     */
    public boolean isCorrect() {
        return false;
    }

    /**
     * Checks if the request is logically applicable by the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the action can be applied by the Model with the given parameters.
     */
    public boolean canBeApplied(ActionController actionController) { return false; }

    /**
     * Computes the request sent by the client using Model's methods.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A String to be used later to create a message to send to the Client.
     */
    public String doAction(ActionController actionController) {
        return null;
    }

    /**
     * Prepares a message to be sent to the Client after a certain request. Each
     * Action SubClass creates a different MessageToClient-Object to be later serialized and sent.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A MessageToClient subclass containing all information needed to update the client's Reduced Model.
     */
    public MessageToClient messagePrepare(ActionController actionController) {
        return null;
    }
}
