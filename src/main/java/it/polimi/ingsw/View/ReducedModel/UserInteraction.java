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


    /**
     * Method used to ask the player if he wants to either Start a new game or connect to an
     * existing one.
     * @param clientConnection Used to send the result (boolean) of the User Interface interaction to the server.
     * @throws Exception if there are problems when sending the message over the socket.
     */
    public void startOrJoin(ClientConnection clientConnection) throws Exception{
        clientConnection.send(ui.startOrJoin());
    }

    /**
     * Method used to ask the player the number of players he wants the game to be composed of.
     * @param clientConnection Used to send the result (int) of the User Interface interaction to the server.
     * @throws Exception if there are problems when sending the message over the socket.
     */
    public void numberOfPlayers(ClientConnection clientConnection) throws Exception {
        clientConnection.send(ui.numberOfPlayers());
    }

    /**
     * Method used to get the username from the player.
     * @param clientConnection Used to send the result (String) of the User Interface interaction to the server.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialChooseName(ClientConnection clientConnection) throws Exception{
        clientConnection.send(ui.initialInsertName());
    }

    /**
     * Method used to display a message while the player waits for all the people to join.
     */
    public void waitingForPlayers() {
        ui.waitingForPlayers();
    }

    /**
     * Method used to put the player inside a lobby where he has to select if he's ready.
     * @param clientConnection Used to send the result (boolean) of the User Interface interaction to the server.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialLobby(ClientConnection clientConnection) throws Exception{
        clientConnection.send(ui.initialLobby());
    }

    /**
     * Method used to make the player choose his two Leader Cards.
     * @param clientConnection Used to send the result (ArrayList<LeaderCard>) of the User Interface interaction to the server.
     * @param leaderCards Four Leader Cards sent by the server to choose from.
     * @throws Exception if there have been problems while serializing on the socket.
     */
    public void initialChooseLeaderCards(ClientConnection clientConnection, ArrayList<RedLeaderCard> leaderCards) throws Exception{
        clientConnection.send(ui.initialChooseLeaderCards(leaderCards));
    }

    /**
     * Method used to make the player choose his first Resources.
     * @param clientConnection Used to send the result (ArrayList<Resources>) of the User Interface interaction to the server.
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
