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
     * Method used to check if the request parameters are actually usable by model's methods.
     * @return false by default as it should always be overridden.
     */
    public boolean isCorrect() {
        return false;
    }

    /**
     * Method used to check if the request is logically applicable inside the model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return false by default as it should always be overridden.
     */
    public boolean canBeApplied(ActionController actionController) { return false; }

    /**
     * Method used to compute the request sent by the client inside the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A String to be used later to create a message to send to the Client.
     */
    public String doAction(ActionController actionController) {
        return null;
    }

    /**
     * Method used to prepare a message to be sent to the Client after a certain request. Each
     * Action SubClass creates a different MessageToClient-Object to be later serialized and sent.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return null by default as it should always be overridden.
     */
    public MessageToClient messagePrepare(ActionController actionController) {
        return null;
    }
}
