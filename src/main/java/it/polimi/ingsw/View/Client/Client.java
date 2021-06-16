package it.polimi.ingsw.View.Client;

import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.View.User.*;
import it.polimi.ingsw.View.Utility.DebuggingTools.Debugger;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerFactory;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.font.ShapeGraphicAttribute;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.*;

/** The client handles the opening of the desired interface and than handles the connection to the server
 */
public class Client {
    static private String serverAddress;
    static private int serverPort;
    private String user;
    private static final UserInteraction userInteraction = new UserInteraction() {
    };
    private final Debugger DEBUGGER = DebuggerFactory.getDebugger(DebuggerType.CLIENT);

    private static boolean cheatMode = false;

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

        // TOGGLE CLIENT SIDE CHEAT MODE VIEWED BY CLIENT
        // Has to also be active on Server to work.
        cheatMode = true;

        /* !! HAS TO BE COMMENTED FOR THE DEBUGGER TO STOP !! */
        Debugger.setAllActive(true);

       try{

         //if (args.length!=0&&args[0].toLowerCase(Locale.ROOT).equals("--cli")) {
            userInteraction.setUi(new CliController());
            Client client = new Client("localhost", 8765);
            client.startUp();
/*
       } else {
            new Thread(() -> Application.launch(Gui.class)).start();
            Gui.waitForStartUp();
        }
*/
       }catch (Exception e) {
            System.err.println("Unable to start a Graphical Interface, shutting down");
            System.err.println(e);
            System.exit(-1);
       }

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
            DEBUGGER.printDebug("Connected to server!");

            ClientConnection clientConnection = new ClientConnection(this, socket);
            new Thread(clientConnection).start();

            if(userInteraction.getUi() instanceof CliController)
                new Thread(new Cli(this, clientConnection)).start();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error in creating connection!");
            Thread.currentThread().interrupt();
            System.exit(-1);
        }
    }
    /**
     * Sets up the connection once the Player has input server and port in the Gui application
     * @param objects   the server and the port
     */
    public ArrayList startUp(ArrayList<Object> objects){
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            //serverAddress and serverPort have to be provided by the user
            serverAddress = (String)objects.get(0);
            serverPort = (int)objects.get(1);

            Socket socket = new Socket(serverAddress, serverPort);
            DEBUGGER.printDebug("Connected to server!");

            ClientConnection clientConnection = new ClientConnection(this, socket);
            userInteraction.setUi(new GuiInitController(this,clientConnection,new ClientExceptionHandler()));
            new Thread(clientConnection).start();

            if(socket.isConnected()){
                arrayList.add(this);
                arrayList.add(clientConnection);
            }else
                arrayList.add(false);
        } catch (IOException e) {
            ClientExceptionHandler clientExceptionHandler = new ClientExceptionHandler();
            try {
                clientExceptionHandler.guiError(e.toString());
            }catch (Exception e1)
            {
                System.err.println("Java Lang Exception in Gui. Shutting down");
                System.exit(-1);
            }
        }
        return arrayList;
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
     */
    public void setUser(String user){
        this.user=user;
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

    public static boolean isCheatMode() {
        return cheatMode;
    }

    public static void setUserInteraction(UserInterface userInterface) { userInteraction.setUi(userInterface);}


}