package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.ControllerClasses.Observable;

public class ModelToView extends Observable<MessageToClient> {

    public ModelToView(){

    }

    @Override
    public void notify(MessageToClient message) {
        super.notify(message);
    }
}
