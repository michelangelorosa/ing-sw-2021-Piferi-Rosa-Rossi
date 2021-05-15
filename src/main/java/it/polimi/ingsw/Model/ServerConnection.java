package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/** ServerConnection handles communication with a single client, for both the inputStream and the outputStream, for now just as objects
 * with <b><u>MANUAL FLUSHING</u></b>
 *
 */

public class ServerConnection implements Runnable {
    private String name;
    private Socket socket;
    private ObjectOutputStream out;
    private Scanner in;
    private ArrayList<ServerConnection> connections;

    public ServerConnection(Socket socket, ArrayList<ServerConnection> connections) throws IOException {
        this.socket = socket;
        this.connections=connections;
    }

    @Override
    public void run() {
        System.out.println("Server Connection Connected to " + socket.getInetAddress());

        Set<String> names = Server.getNames();
        Set<Socket> socketSet = Server.getSockets();

        try {
            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            //Again, for the input
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream =new ObjectInputStream(inputStream);

            MessageToClient messagetoclient = new MessageToClient();

            while (true) {
                System.out.println("Sent request for name please! "+0);
                //Sends a request for a username
                objectOutputStream.writeInt(0);
                objectOutputStream.flush();

                String name = objectInputStream.readUTF();

                if (name == null) {
                    System.err.println("Name is null! Disconnetting user "+socket.toString());
                    return;
                }
                synchronized (names) {
                    if (Server.setName(name)) {
                        System.out.println(name+"'s name is ok!");
                        //TODO add new player
                        break;
                    } else if (!name.isBlank()) {
                        System.err.println("Name is already in use!");
                        //TODO: Reload from saved state
                        break;
                    }
                }
            }
            //Letting everybody know they have a new friend
            /*
            objectOutputStream.writeInt(1);
            System.out.println("Sent reply with "+names.toString());
            objectOutputStream.writeUTF(names.toString());
            objectOutputStream.flush();
            */

            //Accepts messages from client and broadcasts them
            while (true) {
                int temp = objectInputStream.readInt();
                System.out.println("Recieved "+temp);
                if(temp==0){//BROADCAST MESSAGE
                    broadcast(objectInputStream);
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

    private void broadcast(ObjectInputStream objectInputStream){
        for(ServerConnection allClients : connections){
            try {
                allClients.out.writeObject(objectInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}