package it.polimi.ingsw.View.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import it.polimi.ingsw.Controller.ControllerClasses.Observer;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCard;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.View.User.ClientExceptionHandler;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.Utility.DebuggingTools.Debugger;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerFactory;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerType;

/** ClientConnection handles the listening of messages from the server.
 *  the messages are int coded
 */
public class ClientConnection implements Runnable, Observer<Action> {
    private Socket server;
    private Client client;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    private final Debugger DEBUGGER = DebuggerFactory.getDebugger(DebuggerType.CLIENT_CONNECTION);

    /**
     * Tries to create a connection to the server using the client's parameters.
     * @param client        The client
     * @throws IOException  I/O error
     */
    public ClientConnection (Client client, Socket server) throws IOException{
        this.client = client;
        this.server = server;
        objectOutputStream = new ObjectOutputStream(server.getOutputStream());
        objectInputStream = new ObjectInputStream(server.getInputStream());
    }

    /**
     * Listens to the server
     */
    @Override
    public void run(){
        DEBUGGER.printDebug("Started");

        try {
            //First part of the connection
            while (true) {
                int action = objectInputStream.readInt();

                DEBUGGER.printDebug("Got action numbered: "+action);

                //Action = 0, name request
                if (action==0) {
                    DEBUGGER.printDebug("First Loop: choosing name");
                    if(this.client.getUserInteraction()!=null)
                    this.client.getUserInteraction().nextAction(UIActions.CHOOSE_NAME);
                }
                //Action = 1, how many players
                if(action==1){
                    DEBUGGER.printDebug("First Loop: choosing number of players");
                    this.client.getUserInteraction().nextAction(UIActions.CHOOSE_NUMBER_OF_PLAYERS);
                }
                //Opens the lobby, if applicable
                else if(action==2){
                    DEBUGGER.printDebug("First Loop: opening initial lobby");
                    this.client.getUserInteraction().nextAction(UIActions.INITIAL_LOBBY);
                }
                else if(action==3) {
                    //Reconnected, the game has already started
                    DEBUGGER.printDebug("First Loop: reconnecting to Game");
                    this.client.getUserInteraction().setGame(new Game());
                    this.client.getUserInteraction().nextAction(UIActions.RECONNECTION);
                    MessageToClient reconnectionMessage = (MessageToClient) objectInputStream.readObject();
                    reconnectionMessage.updateView(getClient().getUserInteraction());
                    this.client.getUserInteraction().getGame().setMyNickname(this.client.getUser());

                    break;
                }
                else if(action == 4) {
                    //Sets a new Game with the names of the players playing
                    DEBUGGER.printDebug("Setting up new Game");
                    ArrayList<String> playerNames = (ArrayList<String>) objectInputStream.readObject();
                    this.client.getUserInteraction().setPlayers(playerNames);
                    DEBUGGER.printDebug("MY NICKNAME: "+this.client.getUser());
                    this.client.getUserInteraction().getGame().setMyNickname(this.client.getUser());
                }
                else if(action==5){
                    //Accepts messages regarding the Initial Phase of the Game
                    while(true) {
                        Object object = objectInputStream.readObject();
                        if(object instanceof RedLeaderCard[]) {
                            //Reads RedLeaderCards to choose from
                            DEBUGGER.printDebug("Leader card choice block");
                            RedLeaderCard[] leaderCards = (RedLeaderCard[]) object;
                            this.client.getUserInteraction().getGame().setLeaderCards(leaderCards);
                            this.client.getUserInteraction().nextAction(UIActions.INITIAL_CHOOSE_LEADER_CARDS);
                        }
                        else if (object instanceof int[]) {
                            //Reads number of resources to choose from
                            DEBUGGER.printDebug("Resources choice block");
                            int resources = ((int[])object)[0];
                            this.client.getUserInteraction().setInitNumberOfResources(resources);
                            this.client.getUserInteraction().nextAction(UIActions.INITIAL_CHOOSE_RESOURCES);
                        }
                        else if (object instanceof InitChoseLeaderCardsMessage){
                            //Reads a message when a player chooses his Leader Cards
                            DEBUGGER.printDebug("Leader card MESSAGE block");
                            ((InitChoseLeaderCardsMessage)object).updateView(client.getUserInteraction());
                        }
                        else if (object instanceof InitChoseResourcesMessage) {
                            //Reads a message when a player chooses his Resources
                            DEBUGGER.printDebug("Resources MESSAGE block");
                            ((InitChoseResourcesMessage)object).updateView(client.getUserInteraction());
                        }
                        else if (object instanceof GameSetMessage){
                            //Reads a message when every player passed the Initial Phase to set up a new Game with all
                            //Information of the Model's Game.
                            DEBUGGER.printDebug("GameSetMessage MESSAGE block");
                            ((GameSetMessage)object).updateView(getClient().getUserInteraction());
                            break;
                        }
                    }
                    break;
                }
                //Generic error message
                else if(action==9){
                    this.client.getUserInteraction().displayError(objectInputStream.readUTF());
                }
                //Start a new game or continue the saved one? (Persistence)
                else if(action == 11) {
                    this.client.getUserInteraction().nextAction(UIActions.RESTART_OR_CONTINUE);
                }
            }
            //Handling the "MessageToClient" message type
            while (true){
                DEBUGGER.printDebug("Waiting for MessageToClient");
                MessageToClient messageToClient = (MessageToClient) objectInputStream.readObject();
                DEBUGGER.printDebug("GOT MessageToClient!");
                messageToClient.updateView(this.client.getUserInteraction());
                DEBUGGER.printDebug("UPDATED VIEW!");
            }
        } catch (IOException e) {
            DEBUGGER.printDebug("Caught IOException in while loop");
            System.exit(1);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            DEBUGGER.printDebug("Caught ClassNotFoundException in while loop");
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void update(Action action) {
        try {
            send(action);
        }
        catch (IOException e) {
            DEBUGGER.printDebug("Caught IOException in \"update\" method while writing an Action on the socket");
        }
    }

    /**
     * Sends a text message to the server.To be used only when the client wants to give the server a nickname
     * @param   name            the name to be sent
     * @throws  IOException     I/O error
     */
    public synchronized void send(String name) throws IOException {
        DEBUGGER.printDebug("Sending String: " + name);
        objectOutputStream.writeUTF(name);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    /**
     * Sends a LeaderCard object. To be used when selecting a leader card (startup) or in the param modifier
     * @param   card            The card to send
     * @throws  IOException     I/O error
     */
    public synchronized void send(RedLeaderCard card) throws IOException {
        DEBUGGER.printDebug("Sending RedLeaderCard: " + card.getAction());
        objectOutputStream.writeObject(card);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    /**
     * Sends an action to the Server's Controller.
     * @param   action          The action to send
     * @throws  IOException     I/O error
     */
    public synchronized void send(Action action) throws IOException{
        DEBUGGER.printDebug("Sending Action: " + action.getActionType());
        objectOutputStream.writeObject(action);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    /**
     * Sends a boolean to the server.
     * Used for setting the player to be ready to play
     * @param   bool            The boolean to send
     * @throws  IOException     I/O error
     */
    public synchronized void send(Boolean bool) throws IOException{
        DEBUGGER.printDebug("Sending boolean: " + bool);
        objectOutputStream.writeBoolean(bool);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    /**
     * Sends a number to the server.
     * Used for the initial game phase
     * @param   number          The number to send
     * @throws  IOException     I/O error
     */
    public synchronized void send(int number) throws IOException{
        DEBUGGER.printDebug("Sending number: " + number);
        objectOutputStream.writeInt(number);
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    public Client getClient() {
        return client;
    }
}
