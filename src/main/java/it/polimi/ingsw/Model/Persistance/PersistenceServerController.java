package it.polimi.ingsw.Model.Persistance;

import it.polimi.ingsw.Model.Server.Server;

import java.io.IOException;

/**
 * PersistenceServerController Class is used by the server to reset itself if it stopped and not enough players connected
 * after five minutes.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>Server "server": server to check</li>
 *     <li>int "numberOfPlayers": number of the players of the server's game</li>
 * </ul>
 * @author redrick99
 */
public class PersistenceServerController implements Runnable {
    Server server;
    int numberOfPlayers;

    /**
     * Constructor for PersistenceServerController Class.
     */
    public PersistenceServerController(Server server) {
        this.server = server;

    }

    /**
     * An instance of this class is to be run on a thread in parallel with the server.
     * <p>This method makes the object wait for 5 minutes and then resets the server if not enough players connected.</p>
     */
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
