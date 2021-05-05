package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * Action abstract Class defines common Data and Methods to perform an action on the model after a Client's request.
 */
public class Action {
    protected ActionType actionType;
    protected String response = null;

    /**
     * Method used to check if the request parameters are actually usable by model's methods.
     * @return false by default as it should always be overridden.
     */
    public boolean isCorrect() {
        return false;
    }

    /**
     * Method used to check if the request is logically applicable inside the model.
     * @param game Current instance of the Game being played.
     * @return false by default as it should always be overridden.
     */
    public boolean canBeApplied(Game game){
        return false;
    }

    /**
     * Method used to compute the request sent by the client inside the Model.
     * @param game Current instance of the Game being played.
     * @param chooseProductionOutput Needed to save Data for other possible player's requests.
     * @param chooseCardSlot Needed to save Data for other possible player's requests.
     * @param resetWarehouse Needed to save Data for other possible player's requests.
     * @return A String to be used later to create a message to send to the Client.
     */
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse){
        return "super class";
    }

    /**
     * Method used to prepare a message to be sent to the Client after a certain request. Each
     * Action SubClass creates a different MessageToClient-Object to be later serialized and sent.
     * @param game Current instance of the Game being played.
     * @return null by default as it should always be overridden.
     */
    public MessageToClient messagePrepare(Game game) {
        return null;
    }
}
