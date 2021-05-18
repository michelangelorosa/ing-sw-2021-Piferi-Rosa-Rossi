package it.polimi.ingsw.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 *  Handles the connections to new clients, keeping an HashSet of active Names and active Sockets
 *  The default parameters are set to port 8765 for testing purposes.
 *  For every clients that gets connected an instance of ServerConnection is run, the thread pool is fixed at 4 so that
 *  there can't be more than 4 connections handled at the same time, for now they get ignored
 */
public class Server {
    private static Set<String> names = new HashSet<>();
    private static Set<Socket> socketSet = new HashSet<>();
    private static ArrayList<ServerConnection> connections = new ArrayList<>();
    private static int DEFAULT_PORT = 8765;
    private static Game game;
    public static Set<String> getNames() {
        return names;
    }

    public static void setNames(Set<String> names) {
        Server.names = names;
    }

    /**
     * Sets a name in the Name Hash Set
     * @param name the String of the name to insert
     * @return true if the insertion is successful (and thus no other player is called in the same way) or false if the name is already taken
     */
    public static boolean setName(String name){
        if(names.contains(name))
            return false;
            else
        names.add(name);
        return true;
    }

    public static Set<Socket> getSockets() {
        return socketSet;
    }

    public static void setSockets(Set<Socket> socketSet) {
        Server.socketSet = socketSet;
    }

    public static void main(String[] args){
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
        try{
            ServerSocket ss;
            ss = new ServerSocket(server_port);
            var pool = Executors.newFixedThreadPool(4);
            while (true){
                try{
                    Socket client = ss.accept();
                    ServerConnection serverConnection = new ServerConnection(client,connections);
                    connections.add(serverConnection);
                    pool.execute(serverConnection);
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

    public synchronized void broadcast (String string){
        for(ServerConnection allConnections : connections){
            allConnections.sender(null,9);
        }
    }



}
