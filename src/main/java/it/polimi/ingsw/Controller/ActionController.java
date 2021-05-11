package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ActionController Class contains all that is needed to compute a request sent by the Client
 * and to create a object message containing a response.
 */
public class ActionController {

    private final Game game;
    private final ModelToView modelToView = new ModelToView();
    private final ResetWarehouse resetWarehouse = new ResetWarehouse();
    private final ChooseCardSlot chooseCardSlot = new ChooseCardSlot(-1);
    private final ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();

    /**
     * Constructor for ActionController Class.
     */
    public ActionController() {
        this.game = new Game();
    }

    /**
     * Method used to compute the client's request.
     * @param action A generic action received by the server socket.
     */
    public void doAction(Action action) {
        action.doAction(this);
        MessageToClient message = action.messagePrepare(this);

        modelToView.notify(message);
    }

    /**
     * Getter for "game" attribute in ActionController Class.
     */
    public Game getGame() {
        return game;
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
