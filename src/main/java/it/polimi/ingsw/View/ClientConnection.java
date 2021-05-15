package it.polimi.ingsw.View;

import java.io.*;
import java.net.Socket;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.View.ReducedModel.LeaderCard;
import it.polimi.ingsw.Controller.Actions.*;

/** ClientConnection handles the listening of messages from the server.
 * the messages are int coded
 */
public class ClientConnection implements Runnable{
    private Socket server;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Client client;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    /**
     * Tries to create a connection to the server using the client's parameters.
     * @param client        The client
     * @throws IOException  I/O error
     */
    public ClientConnection (Client client) throws IOException{
        this.client=client;
        int port = client.getPort();
        String address = client.getServer();
        server = new Socket(address,port);
        objectInputStream = new ObjectInputStream(inputStream);
        objectOutputStream = new ObjectOutputStream(outputStream);
    }

    /**
     * Listens to the server
     */
    @Override
    public void run(){
        try {
            //First part of the connection
            while (true) {
                int action = objectInputStream.readInt();
                System.out.print("Got "+action);
            //Action = 0, the server is asking the client to input a name!
                if (action==0) {
                    //TODO: asking the client for a name
                    System.out.print(" Enter name: ");
                    String name ="Giorgio";
                    send(name);
                }
                else if(action==1){
                    //Opens the lobby, if applicable, from the lobby a player can himself to be ready, or if no other players are modifying launches the param modifier
                    System.out.println("Lobby");
                }
                else if(action==2){
                    //Opens the param modifier (GUI only!)
                    //To be opened only if no other is modifying

                }
                else if(action==3){
                    //Reconnected, the game has already started

                    break;
                }
                else if(action==4){
                    //Sends the leader cards to pick

                }
                else if(action==5){
                    //Sends the resources to pick from (if applicable) and the inkwell!
                    break;
                }
                else if(action==9){
                    //generic error, could be same name error, game has finished exception et al.
                    System.exit(1);
                }
            }
            while (true){

                //Handling of the Action message type

                Action action = (Action) objectInputStream.readObject();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a text message to the server. To be used only when the client wants to give the server a nickname
     * @param name          the name to be sent
     * @throws Exception    I/O error
     */
    public synchronized void send(String name) throws Exception{
        objectOutputStream.writeUTF(name);
        objectOutputStream.flush();
    }

    /**
     * Sends a LeaderCard object. To be used when selecting a leader card (startup) or in the param modifier
     * @param card          The card to send
     * @throws Exception    I/O error
     */
    public synchronized void send(LeaderCard card) throws Exception{
        objectOutputStream.writeObject(card);
        objectOutputStream.flush();
    }

    /**
     * Sends a DevelopmentCard object. To be used in the param modifier
     * @param card          The card to send
     * @throws Exception    I/O error
     */
    public synchronized void send(DevelopmentCard card) throws Exception{
        objectOutputStream.writeObject(card);
        objectOutputStream.flush();
    }

    /**
     * Sends an action to the Server's Controller.
     * @param action        The action to send
     * @throws Exception    I/O error
     */
    public synchronized void send(Action action) throws Exception{
        objectOutputStream.writeObject(action);
        objectOutputStream.flush();
    }

    /**
     * Sends a boolean to the server.
     * Used for setting the player to be ready to play
     * @param bool
     * @throws Exception
     */
    public synchronized void send(Boolean bool) throws Exception{
        objectOutputStream.writeObject(bool);
        objectOutputStream.flush();
    }

}
