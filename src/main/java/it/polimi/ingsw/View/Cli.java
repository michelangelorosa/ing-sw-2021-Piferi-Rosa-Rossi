package it.polimi.ingsw.View;

import java.util.ArrayList;

public class Cli implements Runnable {
    private ClientConnection clientConnection;
    private final CliController cliController;
    private static boolean ready = false;

    public Cli() {
        this.cliController = new CliController();
    }

    @Override
    public void run() {
        System.out.println("[INFO] Started Client with Cli");

        synchronized (this) {
            while(!ready) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }




    }

    public static boolean isReady() {
        return ready;
    }

    public static void makeReady() {
        Cli.ready = true;
    }

    public static void notReady() {
        Cli.ready = false;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }
}
