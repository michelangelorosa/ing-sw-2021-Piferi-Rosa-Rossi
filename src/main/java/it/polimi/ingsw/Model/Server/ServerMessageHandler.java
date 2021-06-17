package it.polimi.ingsw.Model.Server;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.ParamValidator;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.GameSetMessage;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.Utility.ANSIColors;
import it.polimi.ingsw.View.Utility.DebuggingTools.Debugger;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerFactory;
import it.polimi.ingsw.View.Utility.DebuggingTools.DebuggerType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class ServerMessageHandler {
    private int attempt=0;
    private static final String S = "[SERVER] ";
    private static final String SU = ANSIColors.FRONT_BRIGHT_BLUE + "[SERVER UTILITY] " + ANSIColors.RESET;
    private static final String SE = ANSIColors.FRONT_BRIGHT_RED + "[SERVER ERROR] " + ANSIColors.RESET;
    private final Debugger DEBUGGER = DebuggerFactory.getDebugger(DebuggerType.SERVER_MESSAGE_HANDLER);

    /**
     * nameRequest is the first interaction the server has with the client, it asks for its name and validates it
     * If a game is not running the client is asked to proceed to the Lobby for further setup of the game
     * If a game is running  the client can resume its game
     * @param serverConnection      the server connection
     * @return                      <b>true</b> if the client has to show the lobby
     *                              <b>false</b> if the client has to skip directly to the game
     */
    public boolean nameRequest(ServerConnection serverConnection) {
        String name;
        while(true) {
            DEBUGGER.printDebug("Sent request for Client's name (Action 0)");
            serverConnection.send(0);
            attempt++;
            try {
                name = serverConnection.getIn().readUTF();
                System.out.print("[SmHANDLER] Got name: "+name);
                if(name.isEmpty())
                    sendError(serverConnection, SE + "Name is empty, pick another name!");
                else if(name.isBlank())
                    sendError(serverConnection, SE + "Name is blank, pick another name!");
                else if(name.length() > 16)
                    sendError(serverConnection, SE + "Name is longer than 16 characters, pick another name!");
                else {
                    //If the name is already in the game this value is FALSE, if the name is new it is added to the server and set to TRUE
                    boolean newName = nameInsert(serverConnection,name);
                    System.out.println(", newName: "+newName);
                    //synchronized (serverConnection.getServer().getNames()) {

                    //Name is NOT in the server AND game status is the FIRST player to join
                    if(newName&& Server.getServerStatus().equals(GameStatus.READY)){
                        serverConnection.setName(name);
                        DEBUGGER.printDebug("A first player connected!");
                        //Asking for the number of players to start a game
                        serverConnection.send(1);
                        int numberOfPlayers = serverConnection.getIn().readInt();
                        DEBUGGER.printDebug("Requested to start a game for "+ numberOfPlayers + " players");
                        //Checking if somebody else in the meanwhile has started a new game
                        if(Server.getServerStatus().equals(GameStatus.READY)){
                            serverConnection.getServer().setNumberOfPlayers(numberOfPlayers);
                            serverConnection.getServer().playerReady();
                            return true;
                        }
                        else
                            if(freeSpace(serverConnection)&&isLobby()){
                                serverConnection.setName(name);
                                sendError(serverConnection,"There's another Lobby open, you'll now join!");
                                serverConnection.getServer().playerReady();
                                return true;
                            }
                            else
                                sendError(serverConnection,"You managed to stay on this screen for so long that the lobby is either full or the game is running. How time flies!");
                    }
                    //New Name and game in Lobby mode
                    else if(newName&&isLobby()) {
                        serverConnection.setName(name);
                        DEBUGGER.printDebug("New game in Lobby!");
                        if(freeSpace(serverConnection)){
                                System.out.println(S + "Name: \"" + name + "\" is valid");
                                serverConnection.getServer().playerReady();
                                return true;
                            }
                            else
                                sendError(serverConnection,"This lobby is full!");
                        }
                    //Used name and game in Lobby mode
                    else if(!newName&&isLobby()){
                        DEBUGGER.printDebug("Old name in Lobby!");
                        //TODO: check if the client is alive, if not accept request
                        serverConnection.setName(name);
                        //if client alive return true
                        sendError(serverConnection,"There's another player with the same name, change yours!");
                    }
                    //Name is already in the game and the game is running,
                    else if(!newName&&!isLobby() && Server.getServerStatus() == GameStatus.GAME){
                        DEBUGGER.printDebug("Reconnection attempt");
                        serverConnection.setName(name);
                        if(serverConnection.getServer().canReconnect()){
                            reconnection(serverConnection);
                            return false;
                        }
                            else sendError(serverConnection,"This game is full!");
                        }
                    //Game running, new name
                    else if(!newName&&!isLobby()){
                        DEBUGGER.printDebug("Invalid name for game already running");
                        sendError(serverConnection, SE + "A game is already running in this server and you're not a player!\nGoodbye!");
                        serverConnection.close();
                        }
                    if(attempt>50)
                        serverConnection.close();
                }
            } catch(IOException e) {
                serverConnection.close();
            }
        }
    }

    /**
     * This is the lobby in which a player can wait for other people to join or launch the Param Modifier or try to start the game once enough players are in there
     * @param serverConnection connection used to get the Server and operate on the Controller.
     */
    public void waitingForPlayers(ServerConnection serverConnection) {
        try {
            serverConnection.send(2);
            while (true) {
                int action = serverConnection.getIn().readInt();
                DEBUGGER.printDebug("Got action "+action);
                synchronized (this) {
                    if (action == 3){
                        if(Server.getServerStatus().equals(GameStatus.PARAM))
                            sendError(serverConnection, SE + "Somebody else is already modifying the Game Settings");
                        else if(Server.getServerStatus().equals(GameStatus.LOBBY))
                        {
                            Server.setServerStatus(GameStatus.PARAM);
                            paramModifier(serverConnection);
                            Server.setServerStatus(GameStatus.LOBBY);
                        }
                    }
                    else if(action == 4){
                        serverConnection.setReady(true);
                        serverConnection.waitReady();

                        synchronized (serverConnection.getServer()) {
                            if(Server.getServerStatus() == GameStatus.LOBBY) {
                                DEBUGGER.printDebug("Starting Game!");
                                Server.setServerStatus(GameStatus.LEADER);
                                Set<String> namesSet = serverConnection.getServer().getNames();
                                ArrayList<String> names = new ArrayList<>(namesSet);
                                serverConnection.getServer().getController().getActionController().getGame().gameStartPlayers(names, serverConnection.getServer().getNumberOfPlayers());
                                ArrayList<String> orderedPlayers = serverConnection.getServer().getController().getActionController().getGame().getAllPlayersNameInOrder();

                                serverConnection.getServer().broadcast(4);
                                serverConnection.getServer().broadcast(orderedPlayers);
                            }
                            return;
                        }
                    }

                }
            }
        }catch (IOException | ModelException e){
            serverConnection.close();
        }
    }

    public void initialPhase(ServerConnection serverConnection) throws InterruptedException {
        serverConnection.send(5);
        this.initialLeaderCards(serverConnection);
        serverConnection.waitReady();

        this.initialResources(serverConnection);
        serverConnection.waitReady();

        synchronized (serverConnection.getServer()) {
            DEBUGGER.printDebug("Initial Phase ended!");

            // DEBUGGING
            if(Debugger.isAllActive())
                for(Player player : serverConnection.getServer().getController().getActionController().getGame().getPlayers())
                    DEBUGGER.printDebug(player.getNickname());

            if(Server.getServerStatus() == GameStatus.LEADER) {
                Server.setServerStatus(GameStatus.GAME);
                GameSetMessage message = serverConnection.getServer().getController().getActionController().prepareViewGame();
                serverConnection.getServer().broadcast(message);
            }
        }
    }

    public void initialLeaderCards(ServerConnection serverConnection) throws InterruptedException {
        synchronized (serverConnection.getServer().getController()) {
            while(!serverConnection.isMyTurn()) {
                serverConnection.getServer().getController().wait();
            }
            RedLeaderCard[] initialLeaderCards = serverConnection.getServer().getController().getActionController().getGame().gameStartLeaderCards();
            serverConnection.send(initialLeaderCards);
            Action action = serverConnection.readAction();
            serverConnection.notify(action);
            serverConnection.setReady(true);

            serverConnection.getServer().getController().notifyAll();
        }
    }

    public void initialResources(ServerConnection serverConnection) throws InterruptedException {
        synchronized (serverConnection.getServer().getController()) {
            while (!serverConnection.isMyTurn()) {
                serverConnection.getServer().getController().wait();
            }
            int[] array = new int[1];
            int resources = serverConnection.getServer().getController().getActionController().getGame().gameStartResources(serverConnection.getName());
            array[0] = resources;

            Action action = null;
            while (action == null || !action.canBeApplied(serverConnection.getServer().getController().getActionController())) {
                serverConnection.send(array);
                action = serverConnection.readAction();
                serverConnection.notify(action);
            }
            serverConnection.setReady(true);

            serverConnection.getServer().getController().notifyAll();
        }
    }

    /**
     * paramModifier handles the interaction with the param modifier, the int between 10 and 20 (included) are reserved for use by this application
     * Decoder guide:
     * 10:   Open param modifier
     * int received: corresponding action
     * 10+1: Card modifier                      :card number to modify
     * 10+2: Base Production power modifier
     * 10+3: Vatican Report section modifier    :vatican report to modify
     * 10+4: Victory Points in Faith Track
     * 10+5: Param modifier closing
     */
    public void paramModifier(ServerConnection serverConnection){
        int action;
        try{
            serverConnection.send(10);
            while (true){
                action=serverConnection.getIn().readInt();
                //Card modifier, 1-48 will be a Development Card, 48+ a leader card.
                if(action==11){
                    int cardId=serverConnection.getIn().readInt();
                    if(cardId>48){
                        //Leader card
                        LeaderCard leaderCard;
                        //TODO: get the card from the id
                        //serverConnection.send(leaderCard);
                        leaderCard = (LeaderCard) serverConnection.getIn().readObject();
                        if(ParamValidator.validateCard(leaderCard)){
                            //Add the leader card to the game

                        }else sendError(serverConnection,"Invalid leader card!");
                    }else if(cardId>1){
                        //Development card
                        DevelopmentCard developmentCard;
                        //TODO: get the card from the id
                        //serverConnection.send(developmentCard);
                        developmentCard= (DevelopmentCard) serverConnection.getIn().readObject();
                        if(ParamValidator.validateCard(developmentCard)){
                            //Add card to the game
                        }else sendError(serverConnection,"Invalid development card!");
                    }
                }
                //Base production power modifier
                else if(action==12){

                }
                //Vatican Report Section modifier
                else if (action==13){

                }
                //Victory points in faith track modifier
                else if (action==14){

                }
                else if(action<10||action==15)
                    break;
            }
        }catch (IOException | ClassNotFoundException e){
            serverConnection.close();
        }
    }

    public void sendError(ServerConnection serverConnection, String string) {
        DEBUGGER.printDebug("Sending error to client String: " + string);
        serverConnection.send(9);
        serverConnection.send(string);
    }

    public boolean isLobby(){
        GameStatus serverStatus = Server.getServerStatus();
        //noinspection RedundantIfStatement
        if(serverStatus.equals(GameStatus.LOBBY) || serverStatus.equals(GameStatus.PARAM))
            return true;
        else
            return false;
    }

    /**
     * nameInsert checks if the username is already set in the server, if not the name is inserted into the server
     * @param serverConnection      the reference to the serverConnection
     * @param name                  the name to check
     * @return <b>true</b> if the name is not in the server, <b>false</b> if somebody has the same name
     */
    public synchronized boolean nameInsert(ServerConnection serverConnection,String name){
        return serverConnection.getServer().setName(name);
    }

    /**
     * nameOk checks if the username is in the server
     * @param serverConnection      the reference to the serverConnection
     * @param name                  the name to check
     * @return <b>true</b> if the name is in the server, <b>false</b> otherwise
     */
    public boolean nameOk(ServerConnection serverConnection,String name){
        return serverConnection.getServer().matchName(name);
    }

    /**
     * freeSpace checks if there's space in the Lobby
     * @param serverConnection      the reference to the serverConnection
     * @return true if there's free space to join, false otherwise
     */
    public boolean freeSpace(ServerConnection serverConnection){
        DEBUGGER.printDebug("freeSpace: "+(serverConnection.getServer().getNumberOfPlayers()<serverConnection.getServer().getReadyPlayers()) +" "+serverConnection.getServer().getNumberOfPlayers()+" < "+serverConnection.getServer().getReadyPlayers());
        return serverConnection.getServer().getNumberOfPlayers()>serverConnection.getServer().getReadyPlayers();
    }

    public void reconnection(ServerConnection serverConnection) {
        serverConnection.send(3);
        GameSetMessage message = serverConnection.getServer().getController().getActionController().prepareViewGame();
        serverConnection.getServer().getController().getActionController().getGame().getPlayerByNickname(serverConnection.getName()).setStatus(PlayerStatus.IN_GAME);
        serverConnection.send(message);
        serverConnection.addObserver(serverConnection.getServer().getController());
        serverConnection.getServer().getController().getActionController().getModelToView().addObserver(serverConnection);
        serverConnection.getServer().addConnection(serverConnection);
    }


    //Debug stuff
    public static String getS() {
        return S;
    }

    public static String getSU() {
        return SU;
    }

    public static String getSE() {
        return SE;
    }
}
