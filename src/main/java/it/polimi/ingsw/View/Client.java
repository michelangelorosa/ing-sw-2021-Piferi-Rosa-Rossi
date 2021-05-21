package it.polimi.ingsw.View;

import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.*;

/** The client handles the opening of the desired interface and than handles the connection to the server
 */
public class Client {
    static private String serverAddress;
    static private int serverPort;
    private String user;
    private boolean token;
    private boolean myTurn;
    private ClientConnection clientConnection;
    private static final UserInteraction userInteraction = new UserInteraction() {
    };

    /**
     * Default constructor
     * @param serverAddress The address to connect to
     * @param serverPort    The port to connect to
     */
    public Client(String serverAddress, int serverPort){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Launches the client, if a command line argument "--cli" or "--CLI" is provided the game is started in CLI mode, otherwise in GUI
     * @param args          Command Line Arguments
     */
    public static void main(String[] args){
       try{
        //if (args[0].toLowerCase(Locale.ROOT).equals("--cli")) {
            userInteraction.setUi(new Cli());
        //} else {
            //userInteraction = new UserInteraction(new Gui());
        //}

       }catch (Exception e) {
            System.err.println("Unable to start a Graphical Interface, shutting down");
            System.exit(-1);
       }
        Client client = new Client("localhost", 25565);
        client.startUp();

    }

    /**
     * Once the user is able to interact with the game this method is called to try to establish a connection with the server for both listening and writing on the socket
     */
    public void startUp(){
        try {
            //serverAddress and serverPort have to be provided by the user
            ArrayList<Object> objects = userInteraction.connectToServer();
            serverAddress = (String)objects.get(0);
            serverPort = (int)objects.get(1);

            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server!");

            ClientConnection clientConnection = new ClientConnection(this, socket);
            new Thread(clientConnection).start();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error in creating connection!");
            Thread.currentThread().interrupt();
            System.exit(-1);        }
    }

    /**
     * To get the user's name
     * @return      String username
     */
    public String getUser(){
        return user;
    }

    /**
     * Sets the username of the Player. Has to be set from graphical interface once the username is validated and accepted by the server
     * @param user
     */
    public void setUser(String user){
        this.user=user;
    }

    /**
     * Sets the token value; it has to be set <b>true</b> every time it's the player's turn and <b>false</b> once the token is used
     * @param token
     */
    public void setToken(boolean token) {
        this.token = token;
    }

    /**
     * Sets if it's the user's turn, otherwise the client won't be allowed to send messages
     * @param myTurn
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    /**
     * @return the server the Client is connected to
     */
    public String getServer(){
        return serverAddress;
    }
    /**
     * @return the port the Client is connected to
     */
    public int getPort(){
        return serverPort;
    }

    public UserInteraction getUserInteraction() {
        return userInteraction;
    }
}