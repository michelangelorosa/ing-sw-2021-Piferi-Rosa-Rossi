package it.polimi.ingsw.Controller.ControllerClasses;

import it.polimi.ingsw.Controller.Actions.Action;

/**
 * Controller Class observes messages coming from the Client to compute them
 * as soon as they are notified by a serverConnection object (which extends Observable).
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>ActionController "actionController": contains the Game instance being played by the Clients, along
 *     with methods to compute an Action message and create a MessageToClient to be sent to all Clients</li>
 * </ul>
 * @author redrick99
 */
public class Controller implements Observer<Action> {
    private final ActionController actionController;

    /**
     * Constructor for Controller Class.
     */
    public Controller() {
        this.actionController = new ActionController();
    }

    /**
     * Overridden update method of the Observer Interface. Used to receive
     * Action type messages coming from the Server Socket.
     * @param action The action to be computed.
     */
    @Override
    public void update(Action action) {
        actionController.doAction(action);
    }

    /**
     * Getter for "actionController" attribute.
     */
    public ActionController getActionController() {
        return actionController;
    }
}
