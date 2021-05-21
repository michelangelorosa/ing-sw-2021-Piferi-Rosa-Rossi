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

    public void startOrJoin(ClientConnection clientConnection) throws Exception{
        clientConnection.send(ui.startOrJoin());
    }

    public void numberOfPlayers(ClientConnection clientConnection) throws Exception {
        clientConnection.send(ui.numberOfPlayers());
    }

    /**
     * Method used to get the username from the player.
     * @param clientConnection ClientConnection used to send the username.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialChooseName(ClientConnection clientConnection) throws Exception{
        clientConnection.send(ui.initialInsertName());
    }

    public void waitingForPlayers() {
        ui.waitingForPlayers();
    }

    /**
     * Method used to put the player inside a lobby where he has to select if he's ready.
     * @param clientConnection ClientConnection used to send the boolean.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialLobby(ClientConnection clientConnection) throws Exception{
        clientConnection.send(ui.initialLobby());
    }

    /**
     * Method used to make the player choose his two Leader Cards.
     * @param clientConnection ClientConnection used to send the chosen Leader Cards.
     * @param leaderCards Four Leader Cards sent by the server to choose from.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialChooseLeaderCards(ClientConnection clientConnection, ArrayList<LeaderCard> leaderCards) throws Exception{
        clientConnection.send(ui.initialChooseLeaderCards(leaderCards));
    }

    /**
     * Method used to make the player choose his first Resources.
     * @param clientConnection ClientConnection used to send the chosen Resources.
     * @param resources Number of resources the player has to choose.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialChooseResources(ClientConnection clientConnection, int resources) throws Exception{
        clientConnection.send(ui.initialChooseResources(resources));
    }

    /**
     * Method used to play the turn when the game has actually started.
     */
    public void playTurn() {
        Action action = ui.actionPicker(this.game);
        notify(action);
    }
}
