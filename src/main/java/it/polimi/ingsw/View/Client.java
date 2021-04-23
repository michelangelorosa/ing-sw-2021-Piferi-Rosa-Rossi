package it.polimi.ingsw.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/** This is the Client, and it handles all the Outputs to the server in this class,
 * for testing purposes, for now, the connection is hardcoded to localhost:8765
 * For listening the inputStream from the server it calls clientConnection in a new Thread
 */
public class Client {
    static private String serverAddress="localhost";
    static private int serverPort=8765;

    public static void main(String[] args) throws Exception {

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server!");

            ClientConnection clientConnection = new ClientConnection(socket);
            new Thread(clientConnection).start();

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            while (true) {
                //Gestione OutputStream
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}