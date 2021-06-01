package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.View.ClientConnection;
import it.polimi.ingsw.View.UserInterface;

import java.util.ArrayList;

/**
 * UserInteraction Class contains what a client needs to view the game being played and to access
 * methods to interact with the View to generate messages to be sent to the server.
 */
public abstract class UserInteraction extends Observable<Action> {
    /** ReducedModel version of the game being played by the client */
    private Game game;

    /** UserInterface to be defined when constructing a new UserInteraction instance (either Cli or Gui) */
    private UserInterface ui;

    private int actionNumber = -1;

    /**
     * Constructor for UserInteraction Class.
     */
    public UserInteraction() {
        this.ui = null;
    }

    public void setUi(UserInterface ui) {
        if(this.ui == null)
            this.ui = ui;
    }

    /**
     * Getter for "game" attribute inside UserInteraction Class.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for "game" attribute inside UserInteraction Class.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter for "ui" attribute inside UserInteraction Class.
     */
    public UserInterface getUi() {
        return ui;
    }

    /**
     * Method used to start the UserInterface method required to get from the player the server address
     * and port needed to connect to the server.
     * @return An arrayList containing two objects:
     *          1 - String containing the address
     *          2 - int containing the port
     */
    public ArrayList<Object> connectToServer() {
        return ui.init();
    }

    public void ready() {
        //ui.continueThread(this);
    }

    public int getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(int actionNumber) {
        this.actionNumber = actionNumber;
    }

    public void nextAction(int actionNumber) {
        ui.nextAction(this, actionNumber);
    }
}
