package it.polimi.ingsw.Model.Persistance;

import it.polimi.ingsw.Model.Server.Server;

import java.io.IOException;

public class PersistenceServerController implements Runnable {
    Server server;
    int numberOfPlayers;

    public PersistenceServerController(Server server) {
        this.server = server;

    }

    @Override
    public void run() {
        numberOfPlayers = server.getController().getActionController().getPersistence().getNumberOfPlayers();

        synchronized (this) {
            try {
                System.out.println("WAITING");
                wait(300000L);
                System.out.println("FINISHED WAITING");
            } catch (InterruptedException e) {
                System.out.println("[PSC] caught InterruptedException when waiting");
                e.printStackTrace();
            }
        }

        synchronized (server.getController().getActionController()) {
            int gameSize = server.getController().getActionController().getGame().getPlayers().size();

            if (numberOfPlayers != -1 && server.getConnectedPlayers() < numberOfPlayers && gameSize == 0) {
                try {
                    server.getController().getActionController().getPersistence().resetFile();
                    System.exit(1);
                } catch (IOException e) {
                    System.out.println("[PSC] caught IOException when resetting file");
                    e.printStackTrace();
                }
            }
        }
    }
}
