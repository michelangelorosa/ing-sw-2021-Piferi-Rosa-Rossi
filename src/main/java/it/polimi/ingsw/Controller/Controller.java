package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Model.*;

/**
 * Controller Class observes messages coming from the Client to compute them
 * as soon as they are notified by an observable.
 */
public class Controller implements Observer<Action> {
    /** actionController computes the actions on a certain game instance */
    private final ActionController actionController;
    private final Game game;

    /**
     * Constructor for Controller Class.
     */
    public Controller(Game game) {
        this.actionController = new ActionController();
        this.game = game;
    }

    /**
     * Overridden update method of the Observer Interface. Used to receive
     * constructed messages from the server Socket.
     * @param action The action to be computed.
     */
    @Override
    public void update(Action action) {
        actionController.doAction(action, game);
    }
}
