package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import it.polimi.ingsw.Model.Exceptions.ModelException;

public class ModelToView extends Observable<MessageToClient> {

    public ModelToView(){

    }

    @Override
    public void notify(MessageToClient message) {
        super.notify(message);
    }
}
