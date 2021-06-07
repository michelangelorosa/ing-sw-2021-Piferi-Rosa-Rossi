package it.polimi.ingsw.Model.Server;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import it.polimi.ingsw.Controller.ControllerClasses.Observer;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

import java.io.*;
import java.net.Socket;

/**
 * ServerConnection is a thread running on the server that manages connection to and from the clients.
 * For the messages to be sent a manual flushing has to be performed!
 */

public class ServerConnection extends Observable<Action> implements Runnable, Observer<MessageToClient> {
    private String name;
    private Socket socket;
    private Server server;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final ServerMessageHandler messageHandler;
    private boolean ready = false;

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


    public void run() {
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            InputStream inputStream = this.socket.getInputStream();
            this.out = new ObjectOutputStream(outputStream);
            this.in = new ObjectInputStream(inputStream);
        } catch (IOException e) { System.out.println("Caught exception"); }

        System.out.println("[SERVER] Connection Connected to " + socket.getInetAddress());

        try {
            if(messageHandler.nameRequest(this)){
                System.out.println("[SERVER CONNECTION] Name request TRUE, proceed to display lobby please!");
                messageHandler.waitingForPlayers(this);
            }
            //Accepts messages from client during game phase
            System.out.println("[SERVER CONNECTION] Starting listening to "+socket.toString());
            while (true) {
                Action action = (Action) in.readObject();
                System.out.println("[SERVER CONNECTION] Received an action from "+this.name);

                notify(action);
                //TODO, notify someone
            }
        }catch (Exception e){
            System.out.println("[SERVER CONNECTION] Caught exception from "+this.name);
        }finally{
        if(name!=null){
            System.out.println(name +" is leaving");

            //TODO freezing player
            }
        }

        try{
            socket.close();
        }catch (IOException e){

        }
    }

    @Override
    public synchronized void update(MessageToClient message) {
        send(message);
    }

    /**
     * Tries to send a number to the client. If it fails the connection is closed.
     * @param i     Number to send
     */
    public synchronized void send(int i){
        System.out.println("[SERVER CONNECTION] Sending "+i);
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
     * Tries to send a String to the client. If it fails the connection is closed.
     * @param string The string to be sent
     */
    public synchronized void send(String string) {
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
     * Sends a LeaderCard array
     * @param leaderCards
     */
    public synchronized void send(RedLeaderCard[] leaderCards) {
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
     * @param bool
     */
    public synchronized void send(boolean bool) {
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

    /**
     * Tries to close the socket with the client
     */
    public void close(){
        try{
        socket.close();
        Thread.currentThread().interrupt();
        }catch (IOException e){
            System.err.println("IOError closing socket of "+socket.toString());
        }
    }

    /**
     * Sends to the client if name matches
     * @param name  username
     * @param i     what to send
     */
    public void conditionalSend(String name,int i){
       System.out.print("[SERVER CONNECTION] Conditional send: "+this.name);
       System.out.print(" "+name);
       System.out.println("condition is "+(boolean)(this.name.equals(name)));
        if(this.name.equals(name))
        {
            System.out.print("[SERVER CONNECTION] Sending to "+name);
            System.out.println("the int "+i);
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

    public void setName(String name) {
        this.name = name;
    }

    public Server getServer() {
        return server;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady() {
        this.ready = true;
    }

    public boolean socketEquals(Socket socket){
        if (socket==this.socket)
        return true;
        else return false;
    }
}