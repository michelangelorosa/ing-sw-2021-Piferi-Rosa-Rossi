package it.polimi.ingsw.View;

import java.io.*;
import java.net.Socket;

/** ClientConnection handles the listening of messages from the server.
 * the messages are int coded
 */
public class ClientConnection implements Runnable{
    private Socket server;
    private OutputStream outputStream;
    private InputStream inputStream;


    public ClientConnection (Socket socket) throws IOException{
        server = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public void run(){
        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            while (true) {
                int action = objectInputStream.readInt();
                System.out.print("Got "+action);

                if (action==0) {
                    System.out.print("Enter name: ");
                    String name ="Giorgio";
                } //Tutti gli switch case
                else if(action==1){
                    System.out.println(objectInputStream.readUTF());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
