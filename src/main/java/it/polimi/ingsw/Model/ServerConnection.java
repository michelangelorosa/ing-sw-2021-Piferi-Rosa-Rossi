package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ServerConnection is a thread running on the server that manages connection to and from the clients.
 * For the messages to be sent a manual flushing has to be performed!
 */

public class ServerConnection implements Runnable{
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
            while (true) {
                System.out.println("[SERVER CONNECTION] All players connected!");
                //This'll be read message to client
                int temp = in.readInt();
                System.out.println("Received "+temp);
                if(temp==0){
                    //BROADCAST MESSAGE
                }
            }
        }catch (Exception e){
            System.out.println("[SERVER CONNECTION] Caught exception");
        }finally{
            if(out!=null){
            }
        }if(name!=null){
            System.out.println(name +" is leaving");

            //TODO freezing player
            }

        try{
            socket.close();
        }catch (IOException e){

        }

    }

    /**
     * Tries to send a number to the client. If it fails the connection is closed.
     * @param i     Number to send
     */
    public synchronized void send(int i){
        try{
            out.writeInt(i);
            out.flush();
            out.reset();
        }catch (IOException e){
            //messageHandler.sendError(this, "IOException when sending an int");
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
            //messageHandler.sendError(this, "IOException when sending a string");
            System.err.println("IOException when sending a string to "+socket.toString());
            close();
        }
    }

    /**
     * Sends a LeaderCard array
     * @param leaderCards
     */
    public synchronized void send(LeaderCard[] leaderCards) {
        try {
            out.writeObject(leaderCards);
            out.flush();
            out.reset();
        } catch(IOException e) {
            //messageHandler.sendError(this, "IOException when sending a Leader Card");
            System.err.println("IOException when sending a Leader Card to "+socket.toString());
            close();
        }
    }

    /**
     * Sends a bool to the client
     * @param bool
     */
    public synchronized void send(boolean bool) throws IOException{
        try {
            out.writeBoolean(bool);
            out.flush();
            out.reset();
        } catch(IOException e) {
            //messageHandler.sendError(this, "IOException when sending a boolean");
            System.err.println("IOException when sending a boolean to "+socket.toString());
            close();
        }
    }

    public synchronized void send(MessageToClient message) {
        try {
            out.writeObject(message);
            out.flush();
            out.reset();
        } catch (IOException e) {
            //messageHandler.sendError(this, "IOException when sending a MessageToClient");
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
        if(this.name.equals(name))
            send(i);
    }

    public void conditionalSend(String name,String message){
        if(this.name.equals(name))
            send(message);
    }

    public void conditionalSend(String name,MessageToClient message){
        if(this.name.equals(name))
            send(message);
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
}