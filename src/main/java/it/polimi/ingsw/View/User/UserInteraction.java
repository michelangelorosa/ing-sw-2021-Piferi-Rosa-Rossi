package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

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

    private UIActions uiAction = UIActions.WAITING;

    private ArrayList<RedLeaderCard> initLeaderCards;
    private int initNumberOfResources = -1;
    private MessageToClient previousMessage;
    private MessageToClient message;

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

    public ArrayList<RedLeaderCard> getInitLeaderCards() {
        return initLeaderCards;
    }

    public void setInitLeaderCards(ArrayList<RedLeaderCard> initLeaderCards) {
        this.initLeaderCards = initLeaderCards;
    }

    synchronized public int getInitNumberOfResources() {
        return initNumberOfResources;
    }

    synchronized public void setInitNumberOfResources(int initNumberOfResources) {
        this.initNumberOfResources = initNumberOfResources;
    }

    synchronized public MessageToClient getPreviousMessage() {
        return previousMessage;
    }

    synchronized public void setPreviousMessage(MessageToClient previousMessage) {
        this.previousMessage = previousMessage;
    }

    synchronized public MessageToClient getMessage() {
        return message;
    }

    synchronized public void setMessage(MessageToClient message) {
        this.message = message;
    }

    public boolean sameMessages() {
        return this.previousMessage == this.message;
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

    public UIActions getUiAction() {
        return uiAction;
    }

    public void setUiAction(UIActions uiAction) {
        this.uiAction = uiAction;
    }

    public void nextAction(UIActions action) {
        ui.nextAction(this, action);
    }

    public void displayError(String error) { ui.displayError(error); }
}
