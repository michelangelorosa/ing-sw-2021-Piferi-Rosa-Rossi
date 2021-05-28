package it.polimi.ingsw.View;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.Controller.Actions.*;

/** ClientConnection handles the listening of messages from the server.
 * the messages are int coded
 */
public class ClientConnection implements Runnable, Observer<Action> {
    private Socket server;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Client client;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    /**
     * Tries to create a connection to the server using the client's parameters.
     * @param client        The client
     * @throws IOException  I/O error
     */
    public ClientConnection (Client client, Socket server) throws IOException{
        this.client=client;
        int port = client.getPort();
        String address = client.getServer();
        this.server = server;
        objectOutputStream = new ObjectOutputStream(server.getOutputStream());
        objectInputStream = new ObjectInputStream(server.getInputStream());
    }

    /**
     * Listens to the server
     */
    @Override
    public void run(){
        System.out.println(ANSIColors.FRONT_BRIGHT_CYAN + "[CLIENT CONNECTION] Started" + ANSIColors.RESET);
        Action actionObject;
        Scanner TEMPORARY_SCANNER = new Scanner(System.in);
        try {
            //First part of the connection
            while (true) {
                int action = objectInputStream.readInt();

                System.out.println(ANSIColors.FRONT_BRIGHT_CYAN + "[CLIENT CONNECTION] Got action "+action + ANSIColors.RESET);
                System.out.println();

                //Action = -2, server is asking client if he wants to either start or join the game
                if (action == -2) {
                    this.client.getUserInteraction().startOrJoin(this);
                }
                else if (action == -1) {
                    this.client.getUserInteraction().numberOfPlayers(this);
                }
            //Action = 0, the server is asking the client to input a name!
                else if (action==0) {
                    this.client.getUserInteraction().initialChooseName(this);
                }

                else if(action == -3) {
                    this.client.getUserInteraction().waitingForPlayers();
                }
                else if(action==1){
                    //Opens the lobby, if applicable, from the lobby a player can himself to be ready, or if no other players are modifying launches the param modifier
                    this.client.getUserInteraction().initialLobby(this);
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
                    //TODO get leaderCards from server
                    System.out.println("[CLIENT CONNECTION] You are here");
                    ArrayList<RedLeaderCard> leaderCards = (ArrayList<RedLeaderCard>)objectInputStream.readObject();
                    this.client.getUserInteraction().initialChooseLeaderCards(this, leaderCards);
                }
                else if(action==5){
                    //Sends the resources to pick from (if applicable) and the inkwell!
                    int resources = objectInputStream.readInt();
                    this.client.getUserInteraction().initialChooseResources(this, resources);
                    break;
                }
                else if(action==10000000) {
                    //Game creation and sets the correct userName for the game instantiation
                    this.client.getUserInteraction().setGame(new Game());
                    this.client.getUserInteraction().getGame().setMyNickname(this.client.getUser());

                    //TODO initialize game with information sent by the server
                    break;
                }
                else if(action==9){
                    //generic error, could be same name error, game has finished exception et al.
                    System.exit(1);
                }
            }
            while (true){

                //Handling of the MessageToClient message type
                MessageToClient messageToClient = (MessageToClient) objectInputStream.readObject();
                messageToClient.updateView(this.client.getUserInteraction());
                this.client.getUserInteraction().playTurn();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void update(Action action) {
        try {
            send(action);
        }
        catch (Exception e) {
            System.out.println("Error occurred while writing on the socket");
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
    public synchronized void send(RedLeaderCard card) throws Exception{
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
     * @param bool          The boolean to send
     * @throws Exception    I/O error
     */
    public synchronized void send(Boolean bool) throws Exception{
        objectOutputStream.writeBoolean(bool);
        objectOutputStream.flush();
    }

    /**
     * Sends a boolean to the server.
     * Used for setting the player to be ready to play
     * @param number        The number to send
     * @throws Exception    I/O error
     */
    public synchronized void send(int number) throws Exception{
        objectOutputStream.writeInt(number);
        objectOutputStream.flush();
    }

}
