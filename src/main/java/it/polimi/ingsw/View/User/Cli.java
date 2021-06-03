package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;

public class Cli implements Runnable{
    private ClientConnection clientConnection;
    private final CliController cliController;
    private final Client client;

    public Cli(Client client, ClientConnection clientConnection) {
        this.cliController = new CliController();
        this.client = client;
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        System.out.println("[INFO] Started Client with Cli\n");
        this.client.getUserInteraction().setActionNumber(-1);
        int i;

        while(true) {
            i = this.waitReady();
            try {
                this.actionParser(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void actionParser(int i) throws Exception {
        switch(i) {
            case -2: this.displayAction();
                break;
            case -1: this.displayError();
                break;
            case 0: this.chooseName();
                break;
            case 1: this.numberOfPlayers();
                break;
            case 2: this.initialLobby();
                break;
            case 3: //reconnected, game already started
                break;
            case 4: this.initialLeaderCards();
                break;
            case 5: this.initialResources();
                break;

            case 17: this.turnInteraction();
                break;


            default: System.out.println("Can't understand Message, turning back...");
        }
    }

    public void displayAction() throws Exception {
        cliController.displayAction(this.client.getUserInteraction());

        if(this.client.getUserInteraction().getMessage().imPlaying(this.client.getUserInteraction()))
            this.actionParser(17);
    }

    public void displayError() throws Exception {
        cliController.displayServerError(client.getUserInteraction());

        if(this.client.getUserInteraction().getMessage().imPlaying(this.client.getUserInteraction()))
            this.actionParser(17);
    }

    public void chooseName() throws Exception {
        String nickname = this.cliController.initialInsertName();
        this.clientConnection.send(nickname);
    }

    public void numberOfPlayers() throws Exception {
        int number = this.cliController.numberOfPlayers();
        this.clientConnection.send(number);
    }

    public void initialLobby() throws Exception {
        int number = this.cliController.initialLobby();
        this.clientConnection.send(number);
    }

    public void initialLeaderCards() throws Exception {
        Action action = this.cliController.initialChooseLeaderCards(this.client.getUserInteraction().getGame().getLeaderCards());
        this.clientConnection.send(action);
    }

    public void initialResources() throws Exception {
        Action action = this.cliController.initialChooseResources(this.client.getUserInteraction().getInitNumberOfResources());
        this.clientConnection.send(action);
    }

    public void turnInteraction() throws Exception {
        if(this.client.getUserInteraction().getPreviousMessage() == this.client.getUserInteraction().getMessage()) {
            this.cliController.displayError("Error: MessageToClient object is the same as before");
        }
        else {
            Action action = this.cliController.actionPicker(this.client.getUserInteraction().getGame());
            this.clientConnection.send(action);
        }
    }

    public int waitReady() {
        int action;
        synchronized (this.client.getUserInteraction()) {
            while(this.client.getUserInteraction().getActionNumber() == -1) {
                try {
                    this.client.getUserInteraction().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            action = this.client.getUserInteraction().getActionNumber();
            this.client.getUserInteraction().setActionNumber(-1);
        }
        return action;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }
}
