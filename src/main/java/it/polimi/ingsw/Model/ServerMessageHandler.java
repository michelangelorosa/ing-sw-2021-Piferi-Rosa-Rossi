package it.polimi.ingsw.Model;

import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.View.ANSIColors;

import javax.swing.*;
import java.io.IOException;

public class ServerMessageHandler extends Observable<Object> {
    private static final String S = "[SERVER] ";
    private static final String SU = ANSIColors.FRONT_BRIGHT_BLUE + "[SERVER UTILITY] " + ANSIColors.RESET;
    private static final String SE = ANSIColors.FRONT_BRIGHT_RED + "[SERVER ERROR] " + ANSIColors.RESET;
    private static GameStatus serverStatus;

    private boolean startOrJoin(ServerConnection serverConnection) {
        serverStatus=Server.getServerStatus();
        if(serverStatus.equals(GameStatus.LOBBY)||serverStatus.equals(GameStatus.PARAM))
        while (true) {
            System.out.print(S + "The game is in "+serverStatus.toString());
            System.out.println(S + " mode. Sending -2");
            serverConnection.send(-2);
            try {
                return serverConnection.getIn().readBoolean();
            } catch (IOException e) {
                //sendError(serverConnection, SE + "IOException when reading the starting boolean");
                serverConnection.close();
            }
        }
        else if(serverStatus.equals(GameStatus.GAME))
        {
            //Revive Player, if possible
            return false;
        }
        else if(serverStatus.equals(GameStatus.IDLE))
        {
            //Lets the player play alone? :(
            return false;
        }
        else if(serverStatus.equals(GameStatus.END))
        {
            //Shows victory/lose screen
            return false;
        }
        return false;
    }

    public void startAndNumberOfPlayers(ServerConnection serverConnection) {
        boolean start = this.startOrJoin(serverConnection);
        if(start) {
            while (true) {
                System.out.println(S + "Sent request for Start or Join (Action -1)");
                serverConnection.send(-1);
                try {
                    int number = serverConnection.getIn().readInt();
                    if (number < 0 || number > 4) {
                        sendError(serverConnection, SE + "Invalid number of players");
                    } else {
                        serverConnection.getServer().setNumberOfPlayers(number);
                        return;
                    }
                } catch (IOException e) {
                    //sendError(serverConnection, SE + "IOException when reading an int");
                    serverConnection.close();
                }
            }
        }
    }

    /**
     * nameRequest is the first interaction the server has with the client, it asks for its name and validates it
     * If a game is not running the client is asked to proceed to the Lobby for further setup of the game
     * If a game is running  the client can resume its game
     * @param serverConnection      the server connection
     * @return                      <b>true</b> if the client has to show the startup screens
     *                              <b>false</b> if the client has to skip directly to the game
     */
    public boolean nameRequest(ServerConnection serverConnection) {
        String name;
        while(true) {
            System.out.println(S + "Sent request for Client's name (Action 0)");
            serverConnection.send(0);
            try {
                name = serverConnection.getIn().readUTF();
                if(name.isEmpty())
                    sendError(serverConnection, SE + "Name is empty, pick another name!");
                else if(name.isBlank())
                    sendError(serverConnection, SE + "Name is blank, pick another name!");
                else if(name.length() > 16)
                    sendError(serverConnection, SE + "Name is longer than 16 characters, pick another name!");
                else {
                    synchronized (serverConnection.getServer().getNames()) {
                        //Name OK and game status is LOBBY the name is added to the server
                        if(nameOk(serverConnection,name)&&isLobby()) {
                            System.out.println(S + "Name: \"" + name + "\" is valid");
                            return true;
                        }
                        else if(!nameOk(serverConnection,name)&&!isLobby()){
                            //Name is already in the game and the game is running,
                            //TODO check if the client is alive, otherwise load the state
                            return false;
                        }
                        else if(!nameOk(serverConnection,name)&&isLobby()){
                            sendError(serverConnection, SE + "Name: \"" + name + "\" is already used, pick another name!");
                        }
                    }
                }
            } catch(IOException e) {
                //sendError(serverConnection, SE + "IOException when reading a string");
                serverConnection.close();
            }
        }
    }

    public void waitingForPlayers(ServerConnection serverConnection) {
        serverConnection.send(-3);
        while(true) {
            synchronized (this) {
                if (serverConnection.getServer().getNames().size() == serverConnection.getServer().getNumberOfPlayers()) {
                    serverConnection.send(1);
                    return;
                }
            }
        }
    }

    public void lobbyAndWait(ServerConnection serverConnection) {
        boolean ready;
        try {
            ready = serverConnection.getIn().readBoolean();
            if(ready)
                serverConnection.getServer().playerReady();

        } catch (IOException e) {
            sendError(serverConnection, SE + "IOException when reading boolean");
        }
        while(true) {
            synchronized (this) {
                if (serverConnection.getServer().getReadyPlayers() == serverConnection.getServer().getNumberOfPlayers()) {
                    serverConnection.send(4);
                    return;
                }
            }
        }
    }

    public void sendError(ServerConnection serverConnection, String string) {
        System.out.println(string);
        System.out.println(SU + "Sending error to client");
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
     * nameOk checks if the username is already set in the server
     * @param serverConnection      the reference to the serverConnection
     * @param name                  the name to check
     * @return <b>true</b> if the name is not in the server, <b>false</b> if somebody has the same name
     */
    public boolean nameOk(ServerConnection serverConnection,String name){
        return serverConnection.getServer().setName(name);
    }

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
