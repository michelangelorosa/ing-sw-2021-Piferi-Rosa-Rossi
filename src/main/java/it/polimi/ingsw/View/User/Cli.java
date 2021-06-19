package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;

import java.io.IOException;

/**
 * Cli Class contains methods and attributes used to run the user's Cli Interface as a separate Thread.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>ClientConnection "clientConnection": connection used to send messages to the Server</li>
 *     <li>CliController "cliController": contains all methods used to print Information and to ask the Player for an input</li>
 *     <li>Client "client": contains information about the Game being played and methods that the Cli needs to work</li>
 * </ul>
 * @author redrick99
 */
public class Cli implements Runnable{
    private final ClientConnection clientConnection;
    private final CliController cliController;
    private final Client client;

    /**
     * Constructor for Cli Class.
     */
    public Cli(Client client, ClientConnection clientConnection) {
        this.cliController = new CliController();
        this.client = client;
        this.clientConnection = clientConnection;
    }

    /**
     * Implemented Run method. In consists of a while loop on which the Cli Thread waits for updates made by
     * the UserInteraction of the Client (via the ClientConnection, when it receives messages from the Server).
     * <p>
     * While loop is closed when a final message, indicating the Game's End, is received.
     */
    @Override
    public void run() {
        System.out.println("[INFO] Started Client with Cli\n");
        this.client.getUserInteraction().setUiAction(UIActions.WAITING);
        UIActions action;

        do {
            action = this.waitReady();
            try {
                this.actionParser(action);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (action != UIActions.FINAL_POINTS && action != UIActions.SINGLEPLAYER_END_LOST && action != UIActions.SINGLEPLAYER_END_WON);

        //TODO ending message "thanks for playing"
        System.out.println("[INFO] Closing Cli Thread...");
    }

    /**
     * Parses an UIActions enum into a call to a methods which corresponds to the given action.
     * @param ACTION UIActions enum representing what the Cli has to do.
     * @throws Exception When an exception is thrown by one of the methods used.
     */
    public void actionParser(UIActions ACTION) throws Exception {
        switch(ACTION) {
            case DISPLAY_ACTION: this.displayAction();
                break;
            case DISPLAY_ERROR: this.displayError();
                break;
            case CHOOSE_NAME: this.chooseName();
                break;
            case CHOOSE_NUMBER_OF_PLAYERS: this.numberOfPlayers();
                break;
            case INITIAL_LOBBY: this.initialLobby();
                break;
            case RECONNECTION: //TODO reconnected, game already started
                break;
            case INITIAL_CHOOSE_LEADER_CARDS: this.initialLeaderCards();
                break;
            case INITIAL_CHOOSE_RESOURCES: this.initialResources();
                break;

            case PLAY_TURN: this.turnInteraction();
                break;
            case FINAL_POINTS: this.finalPoints();
                break;

            case SINGLEPLAYER_TURN: this.singleplayerTurn();
                break;

            case SINGLEPLAYER_END_WON: this.singleplayerEndWon();
                break;

            case SINGLEPLAYER_END_LOST: this.singleplayerEndLost();
                break;

            default: System.out.println("Can't understand Message, turning back...");
        }
    }

    /**
     * Displays the action done by a Player. If it's a certain Player's turn after the action, the Cli automatically
     * begins a new interaction to make the Player play his turn.
     * @throws Exception if an error occurs when calling "actionParser" method.
     */
    public void displayAction() throws Exception {
        cliController.displayAction(this.client.getUserInteraction());

        if(this.client.getUserInteraction().getMessage().imPlaying(this.client.getUserInteraction()))
            this.actionParser(UIActions.PLAY_TURN);
    }

    /**
     * Displays an error caused by the action done by a Player. If it's a certain Player's turn after the action, the Cli automatically
     * begins a new interaction to make the Player play his turn.
     * @throws Exception if an error occurs when calling "actionParser" method.
     */
    public void displayError() throws Exception {
        cliController.displayServerError(client.getUserInteraction());

        if(this.client.getUserInteraction().getMessage().imPlaying(this.client.getUserInteraction()))
            this.actionParser(UIActions.PLAY_TURN);
    }

    /**
     * Asks the player for his name and sends it to the Server.
     * @throws IOException if an error occurs while sending the message.
     */
    public void chooseName() throws IOException {
        String nickname = this.cliController.initialInsertName();
        this.clientConnection.getClient().setUser(nickname);
        this.clientConnection.send(nickname);
    }

    /**
     * Asks the player for the number of players of the game and sends it to the Server.
     * @throws IOException if an error occurs while sending the message.
     */
    public void numberOfPlayers() throws IOException {
        int number = this.cliController.numberOfPlayers();
        this.clientConnection.send(number);
    }

    /**
     * Asks the player if he wants to start the game or open the parameter modifier, then sends the player's input to the Server.
     * @throws IOException if an error occurs while sending the message.
     */
    public void initialLobby() throws IOException {
        int number = this.cliController.initialLobby();
        this.clientConnection.send(number);
    }

    /**
     * Asks the player to choose his initial Leader Cards and sends chosen Leader Cards to the Server.
     * @throws IOException if an error occurs while sending the message.
     */
    public void initialLeaderCards() throws IOException {
        Action action = this.cliController.initialChooseLeaderCards(this.client.getUserInteraction().getGame().getLeaderCards());
        this.clientConnection.send(action);
    }

    /**
     * Asks the player to choose his initial Resources and sends chosen Resources to the Server.
     * @throws IOException if an error occurs while sending the message.
     */
    public void initialResources() throws IOException {
        Action action = this.cliController.initialChooseResources(this.client.getUserInteraction().getInitNumberOfResources());
        this.clientConnection.send(action);
    }

    /**
     * Begins a new turn interaction with the player, where a new Action instance is created and sent to the Server.
     * <p>If the MessageToClient read is the same as the previous one sent by the Server, an error is displayed</p>
     * @throws IOException if an error occurs while sending the message.
     */
    public void turnInteraction() throws IOException{
        if(this.client.getUserInteraction().getPreviousMessage() == this.client.getUserInteraction().getMessage()) {
            this.cliController.displayError("Error: MessageToClient object is the same as before");
        }
        else {
            Action action = this.cliController.actionPicker(this.client.getUserInteraction().getGame());
            this.clientConnection.send(action);
        }
    }

    public void singleplayerTurn() throws Exception {
        this.cliController.showToken(this.client.getUserInteraction().getLorenzoToken());
        this.actionParser(UIActions.PLAY_TURN);
    }

    public void singleplayerEndWon() {
        this.cliController.finalSingleplayer(this.client.getUserInteraction().getGame(), true);
    }

    public void singleplayerEndLost() {
        this.cliController.finalSingleplayer(this.client.getUserInteraction().getGame(), false);
    }

    /**
     * Displays final points of all players and declares the winner.
     */
    public void finalPoints() {
        this.cliController.finalPoints(this.client.getUserInteraction().getGame());
    }

    /**
     * Synchronized with the userInteraction, waits until the UiAction of the latter changes from "WAITING" to something else.
     * <p>This method is used with the sole purpose of synchronizing the Cli with messages sent by the Server</p>
     * @return The Action which the player wants to perform.
     */
    public UIActions waitReady() {
        UIActions action;
        synchronized (this.client.getUserInteraction()) {
            while(this.client.getUserInteraction().getUiAction() == UIActions.WAITING) {
                try {
                    this.client.getUserInteraction().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            action = this.client.getUserInteraction().getUiAction();
            this.client.getUserInteraction().setUiAction(UIActions.WAITING);
        }
        return action;
    }
}
