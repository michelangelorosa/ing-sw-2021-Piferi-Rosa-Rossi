package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Observable;

public class ModelToView extends Observable<MessageToClient> {

    public ModelToView(){

    }

    @Override
    public void notify(MessageToClient message) {
        super.notify(message);
    }
}