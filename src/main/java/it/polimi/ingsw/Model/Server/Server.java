package it.polimi.ingsw.Model.Server;

import it.polimi.ingsw.Controller.ControllerClasses.Controller;
import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Persistance.PersistenceServerController;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.Utility.DebuggingTools.Debugger;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerFactory;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 *  Handles the connections to new clients, keeping an HashSet of active Names and active Sockets
 *  The default parameters are set to port 8765.
 *  <p><b>Attributes:</b></p>
 *  <ul>
 *      <li>Set&lt;String&gt; "names": Set containing the names of the players who connected to the server</li>
 *      <li>Integer "numberOfPlayers": number of players of the game</li>
 *      <li>ArrayList&lt;ServerConnection&gt; "connections": contains the server connection of all players who connected to the server</li>
 *      <li>int "DEFAULT_PORT": default port of the server</li>
 *      <li>int "readyPlayers": number of players who are ready for the next server request</li>
 *      <li>GameStatus "serverStatus": indicates the phase of the game of the server</li>
 *      <li>boolean "cheatMode": set tu true if the server has cheats enabled</li>
 *      <li>Debugger "DEBUGGER": debugger specific to the server</li>
 *  </ul>
 * @author michelangelorosa, redrick99
 */
public class Server {
    private Set<String> names = new HashSet<>();
    private Integer numberOfPlayers;
    private final ArrayList<ServerConnection> connections = new ArrayList<>();
    private final static int DEFAULT_PORT = 8765;
    private int readyPlayers = 0;
    private static GameStatus serverStatus;
    private final Controller controller = new Controller();
    private static boolean cheatMode = false;

    private static final Debugger DEBUGGER = DebuggerFactory.getDebugger(DebuggerType.SERVER);

    /**
     * Getter for "names" attribute.
     */
    public Set<String> getNames() {
        return names;
    }

    /**
     * Setter for "names" attribute.
     */
    public void setNames(Set<String> names) {
        this.names = names;
    }

    /**
     * Sets a name in the Name Hash Set.
     * @param name the String of the name to insert.
     * @return true if the insertion is successful (and thus no other player is called in the same way) or false if the name is already taken.
     */
    public boolean setName(String name){
        if(names.contains(name))
            return false;
        else
            names.add(name);
            return true;
    }

    /**
     * Removes a given name from the Set of names.
     * @param name name to remove.
     */
    public void removeFromNames(String name) {
        this.names.remove(name);
    }

    public boolean isNameInConnections(String name) {
        for(ServerConnection connection : connections)
            if(connection.getName().equals(name))
                return true;

        return false;
    }

    /**
     * Checks if a name in the Name is in the Hash Set.
     * @param name the String of the name to insert.
     * @return true if the name is found, false if the name is not found.
     */
    public boolean matchName(String name){
        return names.contains(name);
    }

    /**
     * Main method to be executed on Server Startup.
     */
    public static void main(String[] args){

        // Enables Cheats to be used on Server
        cheatMode = true;

        int server_port=DEFAULT_PORT;
        if(args.length==2)
            if (args[0].toLowerCase(Locale.ROOT).equals("--port"))
                try {
                    if (args[1].length()>=4&&args[1].length()<=5)
                        if(Integer.parseInt(args[1])>1024&Integer.parseInt(args[1])<65535)
                            server_port=Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        System.err.println("Not a valid port! Using "+DEFAULT_PORT);
                    }
        setServerStatus(GameStatus.READY);
        Server server = new Server();
        server.startUp(server_port);
    }

    /**
     * Starts the server and handles client connections.
     * @param server_port port to start the server on.
     */
    public void startUp(int server_port) {

        /* !! HAS TO BE COMMENTED FOR THE DEBUGGER TO STOP !! */
        Debugger.setAllActive(true);

        if(this.controller.getActionController().getPersistence().fileExists()) {
            this.controller.getActionController().getPersistence().readFile();
            if (this.controller.getActionController().getPersistence().isGameStarted()) {
                this.numberOfPlayers = this.controller.getActionController().getPersistence().getNumberOfPlayers();
                System.out.println(this.numberOfPlayers);
                new Thread(new PersistenceServerController(this)).start();
                serverStatus = GameStatus.GAME;
            }
        }

        try{
            ServerSocket ss;
            ss = new ServerSocket(server_port);
            while (true){
                try{
                    Socket client = ss.accept();
                    DEBUGGER.printDebug("Client connected: "+client.toString());
                    ServerConnection serverConnection = new ServerConnection(this, client);

                    if(serverStatus != GameStatus.GAME) {
                        connections.add(serverConnection);
                        controller.getActionController().getModelToView().addObserver(serverConnection);
                        serverConnection.addObserver(controller);
                    }

                    Thread thread = new Thread(serverConnection);
                    thread.start();
                }catch (IOException e){
                    System.err.println("Drop");
                }
            }
        }catch (IOException e){
            System.err.print("Unable to open Server on port "+server_port);
            System.err.println(", probably it's busy!\nShutting down");
            System.exit(1);
        }
    }

    /**
     * Broadcasts a String to every player connected
     * @param string        The string to send
     */
    public synchronized void broadcast (String string){
        for(ServerConnection connection : connections){
            connection.send(string);
        }
    }

    /**
     * Broadcasts an Integer to every player connected
     * @param i             The int to send
     */
    public synchronized void broadcast (int i){
        for(ServerConnection connection : connections){
            connection.send(i);
        }
    }

    /**
     * Broadcasts an ArrayList of Strings to every player connected
     * @param names  ArrayList of strings to send
     */
    public synchronized void broadcast(ArrayList<String> names) {
        for(ServerConnection connection : connections) {
            connection.send(names);
        }
    }

    /**
     * Broadcasts a MessageToClient to every player connected
     * @param message MessageToClient to send
     */
    public synchronized void broadcast(MessageToClient message) {
        for(ServerConnection connection : connections) {
            connection.send(message);
        }
    }

    /**
     * Sends an int to a specific client, if found
     * @param name      The player name to send the data to
     * @param i         The int to send
     */
    public synchronized void sendTo(String name,int i){
        for(ServerConnection connection : connections)
            connection.conditionalSend(name,i);
    }

    /**
     * Sends a string to a specific client, if found
     * @param name      The player name to send the data to
     * @param message   The string to send
     */
    public synchronized void sendTo(String name,String message){
        for(ServerConnection connection : connections)
            connection.conditionalSend(name,message);
    }

    /**
     * Sends a MessageToClient to a specific client, if found
     * @param name              The player name to send the data to
     * @param messageToClient   The MessageToCliend to send
     */
    public synchronized void sendTo(String name, MessageToClient messageToClient){
        for(ServerConnection connection : connections)
            connection.conditionalSend(name,messageToClient);
    }

    /**
     * Sends a MessageToClient to a specific client, if found
     * @param name              The player name to send the data to
     * @param gameType          The type of the game
     */
    public synchronized void sendTo(String name, GameType gameType){
        for(ServerConnection connection : connections)
            connection.conditionalSend(name,gameType);
    }

    /**
     * Sends a MessageToClient to a specific client, if found
     * @param name              The player name to send the data to
     * @param bool              Boolean value
     */
    public synchronized void sendTo(String name, boolean bool){
        for(ServerConnection connection : connections)
            connection.conditionalSend(name,bool);
    }

    /**
     * Sends a MessageToClient to a specific client, if found
     * @param name              The player name to send the data to
     * @param leaderCards       4 Leader cards for the player to choose from
     */
    public synchronized void sendTo(String name, RedLeaderCard[] leaderCards){
        for(ServerConnection connection : connections)
            connection.conditionalSend(name,leaderCards);
    }

    /**
     * Getter for number of players
     * @return the number of players
     */
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Setter for number of player
     * @param number is the number of players in the lobby
     * @return true if the number is correct
     */
    public boolean setNumberOfPlayers(int number) {
        if(number>0&&number<5) {
            DEBUGGER.printDebug("Number of players set to "+number);
            numberOfPlayers = number;
            setServerStatus(GameStatus.LOBBY);
            return true;
        }
        else
            return false;
    }

    /**
     * Adds a player to the playerReady value of the server
     */
    public void playerReady() {
        readyPlayers ++;
        DEBUGGER.printDebug("Players ready in lobby: "+readyPlayers);
    }

    /**
     * Gets the number of players ready from the server
     * @return  the number of players ready to play
     */
    public int getReadyPlayers() {
        return readyPlayers;
    }

    /**
     * Used to know the status of the game in the server
     * @return      the status of the server, based upon the GameStatus Enum
     */
    public static GameStatus getServerStatus() {
        return serverStatus;
    }

    /**
     * Sets the server status following some events
     * @param setState      the status to set the game to
     */
    public static void setServerStatus(GameStatus setState) {
        DEBUGGER.printDebug("[SERVER] Status set to "+setState.toString());
        serverStatus = setState;
    }

    /**
     * Getter for "controller" attribute.
     */
    public synchronized Controller getController() {
        return controller;
    }

    /**
     * Removes a connections if it corresponds to a given socket.
     * @param socket socket of the connection to remove.
     */
    public void removeFromConnections(Socket socket){
        connections.removeIf(connection -> connection.socketEquals(socket));
    }

    /**
     * Checks if all player who connected are ready for the next connection phase.
     * @return True if all players are ready.
     */
    public boolean allAreReady() {
        int readyPlayers = 0;
        for(ServerConnection connection : this.connections)
            if(connection.isReady())
                readyPlayers ++;

        return readyPlayers == numberOfPlayers;
    }

    /**
     * Sets all players connected to ready or not ready.
     * @param ready boolean corresponding to the ready status.
     */
    public synchronized void setAllReady(boolean ready) {
        for(ServerConnection connection : this.connections)
            connection.setReady(ready);
    }

    /**
     * Checks if the Server is in CheatMode.
     * @return true if the Server is in CheatMode.
     */
    public static boolean isCheatMode() {
        return cheatMode;
    }

    /**
     * Checks if a client can reconnect to the server.
     * @return True if the client can reconnect.
     */
    public boolean canReconnect() {
        return this.connections.size() < numberOfPlayers;
    }

    /**
     * Returns true if the given server connections is not inside the list of connections.
     * @param serverConnection to check.
     * @return
     */
    public boolean notConnected(ServerConnection serverConnection) {
        return !this.connections.contains(serverConnection);
    }

    /**
     * Adds a given serverConnection to the list of connections.
     * @param serverConnection connection to add to the list.
     */
    public void addConnection(ServerConnection serverConnection) {
        this.connections.add(serverConnection);
    }

    /**
     * Returns the number of connected players.
     */
    public int getConnectedPlayers() {
        return connections.size();
    }

    /**
     * Checks if the game is empty.
     * @return true if the game inside the server has no players.
     */
    public synchronized boolean gameIsEmpty() {
        return this.controller.getActionController().gameIsEmpty();
    }

    /**
     * Setter for static "cheatMode" attribute.
     */
    public static void setCheatMode(boolean cheatMode) {
        Server.cheatMode = cheatMode;
    }
}
