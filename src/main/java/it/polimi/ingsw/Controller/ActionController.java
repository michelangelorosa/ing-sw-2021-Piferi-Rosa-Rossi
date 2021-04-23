package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ActionController Class contains all that is needed to compute a request sent by the Client
 * and to create a object message containing a response.
 */
public class ActionController {

    private final ModelToView modelToView = new ModelToView();
    private final ResetWarehouse resetWarehouse = new ResetWarehouse();
    private final ChooseCardSlot chooseCardSlot = new ChooseCardSlot(-1);
    private final ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();

    /**
     * Constructor for ActionController Class.
     */
    public ActionController() {

    }

    /**
     * Method used to compute the client's request.
     * @param action A generic action received by the server socket.
     * @param game Instance of the game being played.
     */
    public void doAction(Action action, Game game) {
        action.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        MessageToClient message = action.messagePrepare(game);

        modelToView.notify(message);
    }

    /**
     * Getter for "resetWarehouse" attribute in ActioController Class.
     */
    public ResetWarehouse getResetWarehouse() {
        return resetWarehouse;
    }

    /**
     * Getter for "chooseCardSlot" attribute in ActioController Class.
     */
    public ChooseCardSlot getChooseCardSlot() {
        return chooseCardSlot;
    }

    /**
     * Getter for "chooseProductionOutput" attribute in ActioController Class.
     */
    public ChooseProductionOutput getChooseProductionOutput() {
        return chooseProductionOutput;
    }
}
