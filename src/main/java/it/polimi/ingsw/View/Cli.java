package it.polimi.ingsw.View;

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
            case 0: this.chooseName();
                break;
            case 1: this.numberOfPlayers();
                break;
            case 2: this.initialLobby();
                break;
            case 3: //reconnected, game already started
                break;
            case 4: //leader card choice
                break;
            case 5: //resources choice
                break;


            default: System.out.println("Can't understand Message, turning back...");
        }
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

    public void initialChoose() {

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
