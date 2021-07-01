package it.polimi.ingsw.Model.Server;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import it.polimi.ingsw.Controller.ControllerClasses.Observer;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.Utility.DebuggingTools.Debugger;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerFactory;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ServerConnection is a thread running on the server that manages connection to and from the clients.
 * For the messages to be sent a manual flushing has to be performed!
 */
public class ServerConnection extends Observable<Action> implements Runnable, Observer<MessageToClient> {
    private String name;
    private Socket socket;
    private final Server server;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final ServerMessageHandler messageHandler;
    private boolean ready = false;

    private final Debugger DEBUGGER = DebuggerFactory.getDebugger(DebuggerType.SERVER_CONNECTION);

    /**
     * Default constructor for a ServerConnection class. Tries to establish a connection to a client after it has contacted the server.
     * Given the socket it creates the outputStream, the corresponding ObjectOutputStream and vice versa for the InputStream and the ObjectInputStream.
     * If unable an IOException is caught and the connection is closed.
     * @param client        The socket Server-Client
     */
    public ServerConnection(Server server, Socket client) throws IOException{
        this.server = server;
        this.socket = client;
        this.messageHandler = new ServerMessageHandler();
    }

    /**
     * Implemented Run method of the Runnable Interface. Defines, using a ServerMessageHandler object, the sequence
     * through which a Client, connected to the Server, has to go through to effectively initiate a new Game
     * to be played.
     * <p>The order of communications is divided as follows:</p>
     * <ol>
     *     <li>The Client is asked to input his name and the number of players (if he is the first who connected)</li>
     *     <li>The Client enters a Lobby where he can decide whether e wants to start the game or modify the game parameters</li>
     *     <li>Once all Clients are connected and ready to start, the Initial Phase of the game begins</li>
     *     <li>The actual game then follows the standard Masters Of Renaissance rules.</li>
     * </ol>
     */
    public void run() {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            InputStream inputStream = this.socket.getInputStream();
            this.out = new ObjectOutputStream(outputStream);
            this.in = new ObjectInputStream(inputStream);
        } catch (IOException e) { System.out.println("Caught exception"); }

        DEBUGGER.printDebug("Connection Connected to " + socket.getInetAddress());

        try {
            if(messageHandler.nameRequest(this)){
                DEBUGGER.printDebug("Name request TRUE, proceed to display lobby please!");
                messageHandler.waitingForPlayers(this);
                messageHandler.initialPhase(this);
            }
            //Accepts messages from client during game phase
            DEBUGGER.printDebug("Starting listening to "+socket);
            while (true) {
                Action action = this.readAction();
                DEBUGGER.printDebug("Received action: " + action.getActionType() + " from "+this.name);
                notify(action);
            }
        } catch (Exception e ) {
            DEBUGGER.printDebug("Caught InterruptedException from " + this.name);
            e.printStackTrace();
            if(name!=null){
                /* DISCONNECTION PROCESSING */
                System.out.println(name +" is leaving");
                disconnected();
                DEBUGGER.printDebug(name + " disconnected successfully");
                /*                          */
            }

        }

        try{
            if(!socket.isClosed())
                socket.close();
        }catch (IOException e){
            DEBUGGER.printDebug("Caught IOException when closing socket of "+this.name);
        }
    }

    /**
     * Overridden method of the Observer Interface.
     * <p>It sends a message whenever an action is executed bu the ActionController.</p>
     * @param message MessageToClient to be sent to the client.
     */
    @Override
    public synchronized void update(MessageToClient message) {
        send(message);
    }

    /**
     * Reads an Action object from the socket.
     * @return The Action read.
     */
    public Action readAction() {
        Action action = null;

        try {
            action = (Action) this.in.readObject();
        } catch (IOException e) {
            DEBUGGER.printDebug("IOException when reading an Action from "+socket.toString());
            if(name!=null){
                /* DISCONNECTION PROCESSING */
                System.out.println(name +" is leaving");
                disconnected();
                DEBUGGER.printDebug(name + " disconnected successfully");
                /*                          */
            }
            e.printStackTrace();
            close();
        } catch (ClassNotFoundException e) {
            DEBUGGER.printDebug("ClassNotFoundException when reading an Action from "+socket.toString());
            e.printStackTrace();
            close();
        }
        return action;
    }

    public boolean readBoolean() {
        try {
            return this.in.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Tries to send a number to the client. If it fails the connection is closed.
     * @param i     Number to send
     */
    public synchronized void send(int i){
        DEBUGGER.printDebug("Sending int: "+i);
        try{
            out.writeInt(i);
            out.flush();
            out.reset();
        }catch (IOException e){
            System.err.println("IOException when sending an int to "+socket.toString());
            close();
        }
    }

    /**
     * Tries to send a number to the client. If it fails the connection is closed.
     * @param i     Number to send
     */
    public synchronized void send(int[] i){
        DEBUGGER.printDebug("Sending int[]: "+i[0]);
        try{
            out.writeObject(i);
            out.flush();
            out.reset();
        }catch (IOException e){
            System.err.println("IOException when sending an int to "+socket.toString());
            close();
        }
    }

    /**
     * Tries to send a String to the client. If it fails the connection is closed.
     * @param string The string to be sent
     */
    public synchronized void send(String string) {
        DEBUGGER.printDebug("Sending String: " + string);
        try {
            out.writeUTF(string);
            out.flush();
            out.reset();
        } catch(IOException e) {
            System.err.println("IOException when sending a string to "+socket.toString());
            close();
        }
    }

    /**
     * Sends a RedLeaderCard array
     * @param leaderCards array RedLeaderCards to be sent.
     */
    public synchronized void send(RedLeaderCard[] leaderCards) {
        DEBUGGER.printDebug("Sending RedLeaderCards" + Arrays.toString(leaderCards));
        try {
            out.writeObject(leaderCards);
            out.flush();
            out.reset();
        } catch(IOException e) {
            System.err.println("IOException when sending a Leader Card to "+socket.toString());
            close();
        }
    }

    /**
     * Sends a bool to the client
     * @param bool boolean to be sent.
     */
    public synchronized void send(boolean bool) {
        DEBUGGER.printDebug("Sending boolean: " + bool);
        try {
            out.writeBoolean(bool);
            out.flush();
            out.reset();
        } catch(IOException e) {
            System.err.println("IOException when sending a boolean to "+socket.toString());
            close();
        }
    }

    /**
     * Sends a MessageToClient to the client
     * @param message   the message to send
     */
    public synchronized void send(MessageToClient message) {
        DEBUGGER.printDebug("Sending MessageToClient: " + message.getActionDone());
        try {
            out.writeObject(message);
            out.flush();
            out.reset();
        } catch (IOException e) {
            System.err.println("IOException when sending a MessageToClient to "+socket.toString());
            close();
        }
    }

    public synchronized void send(LeaderCard leaderCard){
        DEBUGGER.printDebug("Sending LeaderCard: " + leaderCard.getAction());
        try{
            out.writeObject(leaderCard);
            out.flush();
            out.reset();
        }catch(IOException e){
            System.err.println("IOException when sending a Leader Card to "+socket.toString());
            close();
        }
    }

    public synchronized void send(DevelopmentCard developmentCard){
        try{
            out.writeObject(developmentCard);
            out.flush();
            out.reset();
        }catch(IOException e){
            System.err.println("IOException when sending a Development Card to "+socket.toString());
            close();
        }
    }

    public synchronized void send(GameType gameType){
        try{
            out.writeObject(gameType);
            out.flush();
            out.reset();
        }catch(IOException e){
            System.err.println("IOException when sending a Development Card to "+socket.toString());
            close();
        }
    }

    public synchronized void send(ArrayList<String> names){
        DEBUGGER.printDebug("ArrayList<String>: " + names);
        try{
            out.writeObject(names);
            out.flush();
            out.reset();
        }catch(IOException e){
            System.err.println("IOException when sending an ArrayList of Strings to "+socket.toString());
            close();
        }
    }

    /**
     * Tries to close the socket with the client
     */
    public void close(){
        try{
            if(this.server.getController().getActionController().getModelToView().containsObserver(this))
                this.server.getController().getActionController().getModelToView().removeObserver(this);
            this.clearObservers();
            this.server.removeFromConnections(this.socket);
            socket.close();
            Thread.currentThread().stop();
        }catch (IOException e){
            System.err.println("IOError closing socket of "+socket.toString());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sends to the client if name matches
     * @param name  username
     * @param i     what to send
     */
    public void conditionalSend(String name,int i){
        DEBUGGER.printDebug("Conditional send: "+this.name+" "+name);
        DEBUGGER.printDebug("condition is "+this.name.equals(name));
        if(this.name.equals(name))
        {
            DEBUGGER.printDebug("Sending to "+name+"the int "+i);
            send(i);
        }
    }

    public void conditionalSend(String name,String message){
        if(this.name.equals(name))
            send(message);
    }

    public void conditionalSend(String name,MessageToClient message){
        if(this.name.equals(name))
            send(message);
    }

    public void conditionalSend(String name, GameType gameType){
        if(this.name.equals(name))
            send(gameType);
    }

    public void conditionalSend(String name,boolean bool){
        if(this.name.equals(name))
            send(bool);
    }

    public void conditionalSend(String name, RedLeaderCard[] leaderCards){
        if(this.name.equals(name))
            send(leaderCards);
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized boolean nameInServer() {
        return this.server.isNameInConnections(this.name);
    }

    public Server getServer() {
        return server;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     * Method used for synchronization purposes between different Server-Client connections.
     * <p>It works buy stopping the caller Thread until all players are ready to proceed to the next phase of
     * the game.</p>
     */
    public void waitReady() {
        synchronized (this.server) {
            if(this.server.allAreReady()) {
                this.server.notifyAll();
                this.server.setAllReady(false);
            }
            else
                try {
                    this.server.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public boolean isMyTurn() {
        return this.name.equals(this.server.getController().getActionController().getGame().getCurrentPlayerNickname());
    }

    public boolean socketEquals(Socket socket){
        return socket == this.socket;
    }

    /**
     * Updates the Model about a disconnection and <b>if the player who disconnected was CURRENTLY playing</b>, uses
     * the Server to broadcast a message to all Clients so that the next player can start his turn.
     */
    public void disconnected() {
        String currentPlayer = this.server.getController().getActionController().getGame().getCurrentPlayerNickname();
        MessageToClient message = this.server.getController().getActionController().disconnected(this.name);
        System.out.println("\n\n " + server.getController().getActionController().getGame().getCurrentPlayerNickname() + "\n");
        this.server.removeFromConnections(this.socket);
        this.removeObserver(this.server.getController());
        this.server.getController().getActionController().getModelToView().removeObserver(this);

        if(this.name.equals(currentPlayer))
            this.server.broadcast(message);
    }


}