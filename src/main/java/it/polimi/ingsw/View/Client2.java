package it.polimi.ingsw.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    static private String serverAddress="localhost";
    static private int server_port=8765;

    public static void main(String[] args) throws Exception {

        try {
            Socket socket = new Socket(serverAddress, server_port);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            System.out.println("Connected to server!");
            while (true) {
                int action = objectInputStream.readInt();
                System.out.print("Got "+action);

                if (action==0) {
                    System.out.print("Enter name: ");
                    String name ="Giorgio";
                    objectOutputStream.writeUTF(name);
                    objectOutputStream.flush();
                } //Tutti gli switch case
                else if(action==1){
                    System.out.println("Qualcosa");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}