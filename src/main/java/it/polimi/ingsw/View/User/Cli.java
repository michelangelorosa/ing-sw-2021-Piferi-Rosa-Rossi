package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;

public class Cli implements Runnable{
    private final ClientConnection clientConnection;
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
        this.client.getUserInteraction().setUiAction(UIActions.WAITING);
        UIActions action;

        do {
            action = this.waitReady();
            try {
                this.actionParser(action);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (action != UIActions.FINAL_POINTS);

        //TODO ending message "thanks for playing"
        System.out.println("[INFO] Closing Cli Thread...");
    }

    public void actionParser(UIActions ACTION) throws Exception {
        switch(ACTION) {
            case DISPLAY_ACTION: this.displayAction();
                break;
            case DISPLAY_ERROR: this.displayError();
                break;
            case CHOOSE_NAME: this.chooseName();
                break;
            case CHOOSE_NUMBER_OF_PLAYERS: this.numberOfPlayers();
                break;
            case INITIAL_LOBBY: this.initialLobby();
                break;
            case RECONNECTION: //reconnected, game already started
                break;
            case INITIAL_CHOOSE_LEADER_CARDS: this.initialLeaderCards();
                break;
            case INITIAL_CHOOSE_RESOURCES: this.initialResources();
                break;

            case PLAY_TURN: this.turnInteraction();
                break;

            case FINAL_POINTS: this.finalPoints();
                break;


            default: System.out.println("Can't understand Message, turning back...");
        }
    }

    public void displayAction() throws Exception {
        cliController.displayAction(this.client.getUserInteraction());

        if(this.client.getUserInteraction().getMessage().imPlaying(this.client.getUserInteraction()))
            this.actionParser(UIActions.PLAY_TURN);
    }

    public void displayError() throws Exception {
        cliController.displayServerError(client.getUserInteraction());

        if(this.client.getUserInteraction().getMessage().imPlaying(this.client.getUserInteraction()))
            this.actionParser(UIActions.PLAY_TURN);
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

    public void finalPoints() {
        this.cliController.finalPoints(this.client.getUserInteraction().getGame());
    }

    public UIActions waitReady() {
        UIActions action;
        synchronized (this.client.getUserInteraction()) {
            while(this.client.getUserInteraction().getUiAction() == UIActions.WAITING) {
                try {
                    this.client.getUserInteraction().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            action = this.client.getUserInteraction().getUiAction();
            this.client.getUserInteraction().setUiAction(UIActions.WAITING);
        }
        return action;
    }
}
