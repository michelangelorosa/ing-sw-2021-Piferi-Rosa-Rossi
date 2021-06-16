package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

import java.util.ArrayList;

/**
 * UserInteraction Class contains what a client needs to view the game being played and to access
 * methods to interact with the View to generate messages to be sent to the server.
 * <p><b>Attributes:</b><p/>
 * <ul>
 *     <li>Game "game": Reduced version of the game used by the user interface to display changes made to the Model's Game</li>
 *     <li>UserInterface "ui": contains common methods shared by Cli and Gui user interfaces</li>
 *     <li>UIActions "uiAction": needed by the Cli for synchronization purposes, represents the turn interaction phase</li>
 *     <li>RedLeaderCard[] "initLeaderCards": contains the initial Leader Cards sent by the Server for the Player to choose from</li>
 *     <li>int "initNumberOfResources": indicates the number of initial resources which the player has to choose at the beginning
 *     of the game</li>
 *     <li>MessageToClient "previousMessage": contains the second-last message sent by the Server. It is used to check if
 *     the Server actually sent a new message.</li>
 *     <li>MessageToClient "message": used to update the Client's View based on information stored in the message itself</li>
 * </ul>
 * @author redrick99
 */
public abstract class UserInteraction extends Observable<Action> {
    private Game game;
    private UserInterface ui;
    private UIActions uiAction = UIActions.WAITING;
    private RedLeaderCard[] initLeaderCards;
    private int initNumberOfResources = -1;
    private MessageToClient previousMessage;
    private MessageToClient message;
    private SoloActionToken lorenzoToken;

    /**
     * Constructor for UserInteraction Class.
     */
    public UserInteraction() {
        this.ui = null;
    }

    /**
     * Setter for "ui" attribute.
     */
    public void setUi(UserInterface ui) {
        //TEST!//
        System.out.println("HEY, I've SET A USER INTERFACE "+ui);
        //if(this.ui == null)
            this.ui = ui;
    }

    /**
     * Getter for "game" attribute.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for "game" attribute.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter for "ui" attribute.
     */
    public UserInterface getUi() {
        return ui;
    }

    /**
     * Getter for "initLeaderCards" attribute.
     */
    public RedLeaderCard[] getInitLeaderCards() {
        return initLeaderCards;
    }

    /**
     * Setter for "initLeaderCards" attribute.
     */
    public void setInitLeaderCards(RedLeaderCard [] initLeaderCards) {
        this.initLeaderCards = initLeaderCards;
    }

    /**
     * Getter for "initNumberOfResources" attribute.
     * <p>Synchronized to avoid parallel conflicts with Cli Thread</p>
     */
    synchronized public int getInitNumberOfResources() {
        return initNumberOfResources;
    }

    /**
     * Setter for "initNumberOfResources" attribute.
     * <p>Synchronized to avoid parallel conflicts with Cli Thread</p>
     */
    synchronized public void setInitNumberOfResources(int initNumberOfResources) {
        this.initNumberOfResources = initNumberOfResources;
    }

    /**
     * Getter for "previousMessage" attribute.
     * <p>Synchronized to avoid parallel conflicts with Cli Thread</p>
     */
    synchronized public MessageToClient getPreviousMessage() {
        return previousMessage;
    }

    /**
     * Setter for "previousMessage" attribute.
     * <p>Synchronized to avoid parallel conflicts with Cli Thread</p>
     */
    synchronized public void setPreviousMessage(MessageToClient previousMessage) {
        this.previousMessage = previousMessage;
    }

    /**
     * Getter for "message" attribute.
     * <p>Synchronized to avoid parallel conflicts with Cli Thread</p>
     */
    synchronized public MessageToClient getMessage() {
        return message;
    }

    /**
     * Setter for "message" attribute.
     * <p>Synchronized to avoid parallel conflicts with Cli Thread</p>
     */
    synchronized public void setMessage(MessageToClient message) {
        this.message = message;
    }

    /**
     * Checks if the Server actually sent a new message.
     * @return true if the just MessageToClient is the same as before.
     */
    public boolean sameMessages() {
        return this.previousMessage == this.message;
    }

    public SoloActionToken getLorenzoToken() {
        return lorenzoToken;
    }

    public void setLorenzoToken(SoloActionToken lorenzoToken) {
        this.lorenzoToken = lorenzoToken;
    }

    /**
     * Calls the UserInterface method required to get from the player the server address
     * and port needed to connect to the server.
     * @return An arrayList containing two objects:
     *          1 - String containing the address
     *          2 - int containing the port
     */
    public ArrayList<Object> connectToServer() {
        return ui.initial();
    }

    /**
     * Getter for "uiAction" attribute.
     */
    public UIActions getUiAction() {
        return uiAction;
    }

    /**
     * Setter for "uiAction" attribute.
     */
    public void setUiAction(UIActions uiAction) {
        this.uiAction = uiAction;
    }

    /**
     * Adds Players to the "game" attribute based on their names.
     * @param playerNames ArrayList containing the name of the Players who joined the Game.
     */
    public void setPlayers(ArrayList<String> playerNames) {
        this.game = new Game();

        ArrayList<Player> players = new ArrayList<>();

        for(int i = 0; i < playerNames.size(); i++)
            players.add(new Player(playerNames.get(i), i, i == 0));

        this.game.setPlayers(players);
    }

    /**
     * Interacts with the UserInterface by calling its "nextAction" method with a certain UIAction to resume the Thread
     * which was waiting for a Message sent by the Server.
     * @param action Represents the action which is to be performed.
     */
    public void nextAction(UIActions action) {
        ui.nextAction(this, action);
    }

    /**
     * Displays an error using the UserInterface.
     * @param error String containing the error message.
     */
    public void displayError(String error) { ui.displayError(error); }
}
