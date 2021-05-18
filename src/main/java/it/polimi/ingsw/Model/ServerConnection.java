package it.polimi.ingsw.Model;

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

public class ServerConnection implements Runnable {
    private String name;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * Default constructor for a ServerConnection class. Tries to establish a connection to a client after it has contacted the server.
     * Given the socket it creates the outputStream, the corresponding ObjectOutputStream and vice versa for the InputStream and the ObjectInputStream.
     * If unable an IOException is caught and the connection is closed.
     * @param client        The socket Server-Client
     * @param connections   //obsolete, probably
     */
    public ServerConnection(Socket client, ArrayList<ServerConnection> connections){
        try {
            this.socket = client;
            OutputStream outputStream = socket.getOutputStream();
            this.out = new ObjectOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            this.in = new ObjectInputStream(inputStream);
        }catch(IOException e){
            System.err.println("IOException while reading socket! Terminating connection to player\n");
            //CLOSE CONNECTION
        }
    }

    @Override
    public void run() {
        System.out.println("Server Connection Connected to " + socket.getInetAddress());

        try {

            while (true) {
                System.out.println("Sent request for name please! "+0);
                //First thing: it expects a UTF string which represents the name of the client
                String name = in.readUTF();
                if(name.isEmpty()||name.isBlank()||name.length()>16) {
                    System.err.println("Invalid name! Disconnetting "+socket.toString());
                    sender(null,9);
                    close();
                }
                //TODO Sync with Server names
                synchronized (name) {
                    if (Server.setName(name)) {
                        System.out.println(name+"'s name is ok!");
                        //TODO add new player, if possible
                        this.name=name;
                        break;
                    } else if (!name.isBlank()) {
                        System.err.println("Name is already in use!");
                        //TODO: Reload from saved state
                        break;
                    }
                }
            }

            //Accepts messages from client during game phase
            while (true) {
                int temp = in.readInt();
                System.out.println("Recieved "+temp);
                if(temp==0){//BROADCAST MESSAGE
                }
                }
        }catch (Exception e){
            System.out.println(e);
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
     * @param i
     */
    public synchronized void sender(String name,int i){
        try{
            if(name.equals(this.name)||name.isBlank()){
            out.writeInt(i);
            out.flush();
            }
        }catch (IOException e){
            System.err.print("Unable to send "+i);
            System.err.println(" to "+socket.toString());
            close();
        }
    }

    /**
     * Tries to send a number to the client. If it fails the connection is closed.
     * @param i      Number to send
     * @param string The string to be sent
     */
    public synchronized void sender(int i,String string){
        try{
            if(name.equals(this.name)||name.isBlank()){
                out.writeInt(i);
            out.writeUTF(string);
            out.flush();
            }
        }catch (IOException e){
        System.err.print("Unable to send "+i+string);
        System.err.println(" to "+socket.toString());
        close();
        }
    }

    /**
     * Sends a LeaderCard array
     * @param leaderCards
     */
    public synchronized void sender(LeaderCard[] leaderCards){
        try{
            if(name.equals(this.name)||name.isBlank()){
                out.writeInt(4);
            out.writeObject(leaderCards);
            out.flush();
            }
        }catch (IOException e){
            System.err.print("Unable to send leader cards");
            System.err.println(" to "+socket.toString());
            close();
        }
    }

    /**
     * Sends a bool to the client
     * @param bool
     */
    public synchronized void sender(boolean bool){
        try{
            if(name.equals(this.name)||name.isBlank()){
                out.writeObject(bool);
            out.flush();
            }
        }catch (IOException e){
            System.err.print("Unable to send "+bool);
            System.err.println(" to "+socket.toString());
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
}