package it.polimi.ingsw.Model.Server;

import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.ParamValidator;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.io.IOException;
import java.util.ArrayList;

public class ServerMessageHandler extends Observable<Object> {
    private static final String S = "[SERVER] ";
    private static final String SU = ANSIColors.FRONT_BRIGHT_BLUE + "[SERVER UTILITY] " + ANSIColors.RESET;
    private static final String SE = ANSIColors.FRONT_BRIGHT_RED + "[SERVER ERROR] " + ANSIColors.RESET;

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
            System.out.println(S + "Sent request for Client's name (Action 0)");
            serverConnection.send(0);
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
                        System.out.println("[SmHANDLER] A first player connected!");
                        //Asking for the number of players to start a game
                        serverConnection.send(1);
                        int numberOfPlayers = serverConnection.getIn().readInt();
                        System.out.println("[SmHANDLER] Requested to start a game for "+numberOfPlayers);
                        //Checking if somebody else in the meanwhile has started a new game
                        if(Server.getServerStatus().equals(GameStatus.READY)){
                            serverConnection.getServer().setNumberOfPlayers(numberOfPlayers);
                            serverConnection.getServer().playerReady();
                            return true;
                        }
                        else
                            if(freeSpace(serverConnection)&&isLobby()){
                                sendError(serverConnection,"There's another Lobby open, you'll now join!");
                                serverConnection.getServer().playerReady();
                                return true;
                            }
                            else
                                sendError(serverConnection,"You managed to stay on this screen for so long that the lobby is either full or the game is running. How time flies!");
                    }
                    //New Name and game in Lobby mode
                    else if(newName&&isLobby()) {
                        System.out.println("[SmHANDLER] New game in Lobby!");
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
                        System.out.println("[SmHANDLER] Old name in Lobby!");
                        //TODO: check if the client is alive, if not accept request
                        //if client alive return true
                        sendError(serverConnection,"There's another player with the same name, change yours!");
                    }
                    //Name is already in the game and the game is running,
                    else if(!newName&&!isLobby()){
                        System.out.println("[SmHANDLER] Reconnection attempt");
                        if(freeSpace(serverConnection)){
                            //TODO check if the client is alive, if not load the state
                            return false;
                        }
                            else sendError(serverConnection,"This game is full!");
                        }
                    //Game running, new name
                    else if(!newName&&!isLobby()){
                        System.out.println("[SmHANDLER] Invalid name for game already running");
                        sendError(serverConnection, SE + "A game is already running in this server and you're not a player!\nGoodbye!");
                        serverConnection.close();
                        }
                    //}
                }
            } catch(IOException e) {
                serverConnection.close();
            }
        }
    }

    /**
     * This is the lobby in which a player can wait for other people to join or launch the Param Modifier or try to start the game once enough players are in there
     * @param serverConnection
     */
    public void waitingForPlayers(ServerConnection serverConnection) {
        try {
            serverConnection.send(2);
            while (true) {
                synchronized (this) {
                    if (serverConnection.getIn().readInt() == 3){
                        if(Server.getServerStatus().equals(GameStatus.PARAM))
                            sendError(serverConnection, SE + "Somebody else is already modifying the Game Settings");
                        else if(Server.getServerStatus().equals(GameStatus.LOBBY))
                        {
                            Server.setServerStatus(GameStatus.PARAM);
                            paramModifier(serverConnection);
                            Server.setServerStatus(GameStatus.LOBBY);
                        }
                    }
                    else if(serverConnection.getIn().readInt() == 4){
                        if(Server.getServerStatus().equals(GameStatus.PARAM))
                            sendError(serverConnection,SE + "Unable to start the game if somebody is modifying the game settings!");
                        if(Server.getServerStatus().equals(GameStatus.LOBBY))
                            if(serverConnection.getServer().getReadyPlayers()==serverConnection.getServer().getNumberOfPlayers())
                            {
                                System.out.println("Hurray, starting game!");
                                Server.setServerStatus(GameStatus.LEADER);
                                //We should create the Game here somehow
                                //TODO pulire lista giocatori
                                ArrayList<String> names = new ArrayList<>(serverConnection.getServer().getNames());
                                serverConnection.getServer().getController().getActionController().getGame().gameStart(names);

                            }
                            else
                                sendError(serverConnection,"Not enough players ready, need another "+(serverConnection.getServer().getReadyPlayers()-serverConnection.getServer().getNumberOfPlayers()));
                    }

                }
            }
        }catch (IOException e){
            serverConnection.close();
        }
    }

    /**
     * paramModifier handles the interaction with the param modifier, the int between 10 and 20 (included) are reserved for use by this application
     * Decoder guide:
     * 10:   Open param modifier
     * int recived: corresponding action
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
        System.out.println(SU + "Sending error to client");
        System.out.println(string);
        serverConnection.send(9);
        serverConnection.send(string);
    }

    public boolean isLobby(){
        GameStatus serverStatus = Server.getServerStatus();
        if(serverStatus.equals(GameStatus.LOBBY) || serverStatus.equals(GameStatus.PARAM))
            return true;
        else return false;
    }

    /**
     * nameInsert checks if the username is already set in the server, if not the name is inserted into the server
     * @param serverConnection      the reference to the serverConnection
     * @param name                  the name to check
     * @return <b>true</b> if the name is not in the server, <b>false</b> if somebody has the same name
     */
    public boolean nameInsert(ServerConnection serverConnection,String name){
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
        System.out.print("[SmHANDLER] freeSpace: "+(serverConnection.getServer().getNumberOfPlayers()<serverConnection.getServer().getReadyPlayers()));
        System.out.print(serverConnection.getServer().getNumberOfPlayers()+"<");
        System.out.println(serverConnection.getServer().getReadyPlayers());
        return serverConnection.getServer().getNumberOfPlayers()>serverConnection.getServer().getReadyPlayers();
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
