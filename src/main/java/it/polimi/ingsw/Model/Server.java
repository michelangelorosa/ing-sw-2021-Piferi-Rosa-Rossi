package it.polimi.ingsw.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 *  Handles the connections to new clients, keeping an HashSet of active Names and active Sockets
 *  The default parameters are set to port 8765 for testing purposes.
 *  For every clients that gets connected an istance of ServerConnection is run, the thread pool is fixed at 4 so that
 *  there can't be more than 4 connections handled at the same time, for now they get ignored
 */
public class Server {
    private static Set<String> names = new HashSet<>();
    private static Set<Socket> socketSet = new HashSet<>();
    private static ArrayList<ServerConnection> connections = new ArrayList<>();
    private static int S_PORT = 8765;

    public static Set<String> getNames() {
        return names;
    }

    public static void setNames(Set<String> names) {
        Server.names = names;
    }

    public static Set<Socket> getSockets() {
        return socketSet;
    }

    public static void setSockets(Set<Socket> socketSet) {
        Server.socketSet = socketSet;
    }

    public static void main(String[] args){
        try{
            ServerSocket ss;
            ss = new ServerSocket(S_PORT);
            var pool = Executors.newFixedThreadPool(4);
            while (true){
                try{
                    Socket client = ss.accept();
                    ServerConnection serverConnection = new ServerConnection(client,connections);
                    connections.add(serverConnection);
                    pool.execute(serverConnection);
                }catch (IOException e){
                    System.out.println("Drop ");
                }
            }
        }catch (IOException e){
            System.out.println("Unable to open Server on port "+S_PORT);
            System.exit(1);
        }

    }

    public int getPORT() {
        return S_PORT;
    }
    public void setPORT(int n) throws Exception {
        if(n>1024&n<65535)
            S_PORT=n;
        else{
            throw new Exception("Reserved port!");
        }
    }
}
