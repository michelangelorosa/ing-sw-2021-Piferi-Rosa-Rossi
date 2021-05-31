package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ActionController Class contains all that is needed to compute an Action sent by the Client
 * and to create a MessageToClient message containing a response.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>Game "game": Instance of the Game that all connected players are playing on</li>
 *     <li>ModelToView "modelToView": Instance of a Class that contains data and methods needed
 *     to send a created MessageToClient to all Clients</li>
 *     <li>ResetWarehouse "resetWarehouse": Stores information at the start of a <i>Add Cycle</i> so that the player
 *     can reset his Warehouse to starting conditions</li>
 *     <li>ChooseCardSlot "chooseCardSlot": Stores information to be used when a player has finished buying
 *     a Development Card to remember which Development Card he wanted to buy</li>
 *     <li>ChooseProductionOutput "chooseProductionOutput": Stores information to be used when a player has finished
 *     paying for a Production to remember which different types of productions he started</li>
 * </ul>
 * @author redrick99
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
     * Getter for "game" attribute.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for "resetWarehouse" attribute.
     */
    public ResetWarehouse getResetWarehouse() {
        return resetWarehouse;
    }

    /**
     * Getter for "chooseCardSlot" attribute.
     */
    public ChooseCardSlot getChooseCardSlot() {
        return chooseCardSlot;
    }

    /**
     * Getter for "chooseProductionOutput" attribute.
     */
    public ChooseProductionOutput getChooseProductionOutput() {
        return chooseProductionOutput;
    }
}
