package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.ControllerClasses.Observable;

/**
 * ModelToView Class contains methods used to notify a MessageToClient
 */
public class ModelToView extends Observable<MessageToClient> {

    /**
     * Constructor for ModelToView Class.
     */
    public ModelToView(){

    }

    /**
     * Notifies a MessageToClient
     * @param message MessageToClient to be notified.
     */
    @Override
    public void notify(MessageToClient message) {
        super.notify(message);
    }
}
