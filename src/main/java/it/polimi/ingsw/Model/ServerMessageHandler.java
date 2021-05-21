package it.polimi.ingsw.Model;

import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.View.ANSIColors;

import java.io.IOException;

public class ServerMessageHandler extends Observable<Object> {
    private static final String S = "[SERVER] ";
    private static final String SU = ANSIColors.FRONT_BRIGHT_BLUE + "[SERVER UTILITY] " + ANSIColors.RESET;
    private static final String SE = ANSIColors.FRONT_BRIGHT_RED + "[SERVER ERROR] " + ANSIColors.RESET;

    private boolean startOrJoin(ServerConnection serverConnection) {
        while (true) {
            System.out.println(S + "Sent request for Start or Join (Action -2)");
            serverConnection.send(-2);
            try {
                return serverConnection.getIn().readBoolean();
            } catch (IOException e) {
                sendError(serverConnection, SE + "IOException when reading the starting boolean");
            }
        }
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
                    sendError(serverConnection, SE + "IOException when reading an int");
                }
            }
        }
    }

    public void nameRequest(ServerConnection serverConnection) {
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
                        if(serverConnection.getServer().setName(name)) {
                            System.out.println(S + "Name: \"" + name + "\" is valid");
                            serverConnection.setName(name);
                            return;
                        }
                        else
                            sendError(serverConnection, SE + "Name: \"" + name + "\" is already used, pick another name!");
                    }
                }
            } catch(IOException e) {
                sendError(serverConnection, SE + "IOException when reading a string");
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
