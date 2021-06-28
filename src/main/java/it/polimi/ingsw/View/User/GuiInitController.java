package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView ;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
/**
 * GuiInitController is the Class that contains all the methods used for the implementation of the JavaFX gui for the first screens up until before the Board is created.
 * This class also contains all the helpers needed for the GuiBoard class to work.
 */

public class GuiInitController implements UserInterface {
    protected static Stage guiStage;
    protected Client client;
    protected MediaPlayer mediaPlayer;
    protected ClientConnection clientConnection;
    protected GuiBoardController board;
    private boolean firstRun = true;
    protected Stage winnerStage;
    @FXML private Button nameConfirm;
    @FXML private Label score;
    @FXML private Label winner;
    @FXML private TextField server;
    @FXML private TextField port;
    @FXML private Button connect;
    @FXML protected Button confirmLeader;
    @FXML private TextField name;
    @FXML protected CheckBox card1;
    @FXML protected CheckBox card2;
    @FXML protected CheckBox card3;
    @FXML protected CheckBox card4;
    @FXML protected CheckBox card5;
    @FXML protected ImageView card1image;
    @FXML protected ImageView card2image;
    @FXML protected ImageView card3image;
    @FXML protected ImageView card4image;
    @FXML protected ImageView card5image;
    @FXML private ChoiceBox<String> numberPlayers;
    @FXML private Button playerNumberConfirm;
    @FXML private Button stopMusic;
    @FXML private Button readyToPlay;
    @FXML private Button button;
    protected ClientExceptionHandler gui;
    protected ActionEvent event;
    protected Font Baskerville;
    protected Font Dominican;

    /**
     * Constructor for the GuiInitController class
     * @param client                    The client class, which has the references to the UserInteraction class, hence the Game information
     * @param clientConnection          The clientConnection class, used for handling outbound and inbound messages
     * @param clientExceptionHandler    The clientExceptionHandler, which handles some verification of the input and prompts the errors for the gui
     */
    public GuiInitController(Client client,ClientConnection clientConnection,ClientExceptionHandler clientExceptionHandler) {
        this.client=client;
        //Client.setUserInteraction(this);
        this.clientConnection=clientConnection;
        this.gui=clientExceptionHandler;
        Baskerville=Font.loadFonts(getClass().getResource("Assets/Fonts/Baskerville.ttc").toExternalForm(),20)[0];
        Dominican=Font.loadFont(getClass().getResource("Assets/Fonts/Dominican.ttf").toExternalForm(),38);
    }

    /**
     * Method used to switch stages
     * @return  the stage so that it can be modified
     */
    public static Stage getStage() {
        return guiStage;
    }

    /**
     * Method used to set a new stage to be seen
     * @param stage the stage to set
     */
    public static void setStage(Stage stage) {
        guiStage=stage;
    }

    /**
     * Getter for the ClientConnection for network interactions
     * @return  the client connection
     */
    protected ClientConnection getClientConnection(){
        return this.clientConnection;
    }

    /**
     * The popup for notifying the user of the end of the game and its status as a player (Winner/Loser)
     * @throws URISyntaxException   If the music path is formatted badly
     * @throws IOException          If there are problems reading the FXML
     */
    public void finalScore() throws URISyntaxException, IOException {
        Parent winnerMessage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Loser.fxml"));
        loader.setController(this);
        winnerMessage = loader.load();
        Scene scene = new Scene(winnerMessage);

        winnerStage = new Stage();
        winnerStage.initModality(Modality.APPLICATION_MODAL);

        //Get final score
        int points =clientConnection.getClient().getUserInteraction().getGame().getMyPlayer().getVictoryPoints();
        score.setText(Integer.toString(points));
        if(clientConnection.getClient().getUserInteraction().getGame().getMyPlayer().getStatus()==PlayerStatus.WON)
        {
            winner.setText("You Won");
            music(getClass().getResource("Assets/Music/Alleluia.mp3").toExternalForm());
            winnerStage.setTitle("— W i n n e r –");

        }else{
            winner.setText("You Lost");
            music(getClass().getResource("Assets/Music/Zigeunerweisen.mp3").toExternalForm());
            winnerStage.setTitle("Loser");
        }
        winnerStage.setScene(scene);
        winnerStage.showAndWait();
    }

    /**
     * Used to play music within the game
     * @param string getClass().getResource("Assets/Music/FileToPlay.mp3").toExternalForm()
     */
    public void music(String string){
        Media media;
        System.out.println(string);
        media = new Media(string);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
    }

    /**
     * Asks the user for a name
     * @throws IOException  If there's an error reading the FXML file
     */
    public void nameSelection() throws IOException {
        //this.event=event;
        Parent nameSelection;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Name.fxml"));
        loader.setController(this);
        nameSelection = loader.load();
        Scene scene = new Scene(nameSelection);

        //Getting the stage information
        Stage window;
        window = getStage();
        window.setScene(scene);
    }

    /**
     * Asks the user for the number of players which he wants to create a game with, if necessary
     * @throws IOException  If it fails to load the FXML file
     */
    public void playerNumberSelection() throws IOException{
        Parent playerNumberSelection;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/PlayerNumber.fxml"));
        loader.setController(this);
        playerNumberSelection = loader.load();

        Scene scene = new Scene(playerNumberSelection);
        Stage window = getStage();
        window.setScene(scene);

        ObservableList<String> playerNumbers = FXCollections.observableArrayList("1 Player","2 Players","3 Players","4 Players");
        this.numberPlayers.setItems(playerNumbers);
    }

    /**
     * The Lobby screen in which a player can set himself to be ready
     * @throws IOException  If there's a problem reading the FXML file
     */
    public void lobby() throws IOException{
        Parent lobby;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Lobby.fxml"));
        loader.setController(this);
        lobby = loader.load();

        Scene scene = new Scene(lobby);
        //Getting the stage information
        Stage window = getStage();
        window.setScene(scene);

        music(getClass().getResource("Assets/Music/Up Up Up.mp3").toExternalForm());
    }

    /**
     * Gets the name from the name's Text Field and calls the nameValidator in ClientExceptionHandler it for validation.
     * If validation is successful the name is sent
     * @throws Exception    If there are problems with the connection
     */
    public void nameValidation() throws Exception {
        String player=name.getText();
        System.out.println(player);
        if(gui.nameValidator(player))
        {
            this.clientConnection.getClient().setUser(player);
            this.clientConnection.send(player);
        }
    }

    /**
     * Validates the number of players to be sent to the server and then sends them to the server
     * @throws IOException  If there are problems with the connection
     */
    public void playerNumberValidation() throws IOException {
        if(numberPlayers.getValue()!=null){
        System.out.println("Selected players: "+(numberPlayers.getSelectionModel().getSelectedIndex()+1));
        this.clientConnection.send(numberPlayers.getSelectionModel().getSelectedIndex()+1);}
        else displayError("Please select the number of players");
    }

    /**
     * Gets the leader card and shows them to the player
     * @throws Exception
     */
    public void leaderCardSelection() throws Exception{
        RedLeaderCard[] leaderCards = this.client.getUserInteraction().getGame().getLeaderCards();

        Image card1 = getImage(leaderCards[0].getCardId(),true);
        Image card2 = getImage(leaderCards[1].getCardId(),true);
        Image card3 = getImage(leaderCards[2].getCardId(),true);
        Image card4 = getImage(leaderCards[3].getCardId(),true);

        Parent leaderCard;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Leader.fxml"));
        loader.setController(this);
        leaderCard = loader.load();
        Scene scene = new Scene(leaderCard);

        //Getting the stage information
        Stage window = getStage();
        window.setScene(scene);
        card1image.setImage(card1);
        card2image.setImage(card2);
        card3image.setImage(card3);
        card4image.setImage(card4);
        leaderCardCheck();
    }

    /**
     * Quits the game, invoked if the user clicks on "Exit" button
     * @param event
     */
    public void close(ActionEvent event){
        System.exit(0);
    }

    /**
     * Stops the music in the Lobby if the user wants to
     */
    public void stopMedia(){
        stopMusic.setDisable(true);
        if(mediaPlayer!=null)
        mediaPlayer.stop();
    }

    public void setReady(ActionEvent event) throws IOException{
        this.event=event;
        readyToPlay.setDisable(true);
        this.clientConnection.send(4);
    }

    /**
     * Checks that the user has picked two leader cards and enables the cards to be pickable into a checkbox.
     * If the user has picked 2 leader Cards those are sent to the server, otherwise the user is informed of the error
     */
    protected void leaderCardCheck(){
        RedLeaderCard[] leaderCards = this.client.getUserInteraction().getGame().getLeaderCards();
        card1image.setPickOnBounds(true);
        card2image.setPickOnBounds(true);
        card3image.setPickOnBounds(true);
        card4image.setPickOnBounds(true);

        card1image.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                card1.setSelected(!card1.isSelected());
            }
        });
        card2image.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                card2.setSelected(!card2.isSelected());
            }
        });
        card3image.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                card3.setSelected(!card3.isSelected());
            }
        });
        card4image.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                card4.setSelected(!card4.isSelected());
            }
        });
        confirmLeader.setOnAction((EventHandler) event1 -> {
            int selected=0;
            RedLeaderCard[] chosen;
            chosen=new RedLeaderCard[2];
            if(card1.isSelected()){
                selected++;
            }
            if(card2.isSelected()){
                selected++;
            }
            if(card3.isSelected()){
                selected++;
            }
            if(card4.isSelected()){
                selected++;
            }
            if(selected==2){
                try {
                    int choice=0;
                    if(card1.isSelected()){
                        chosen[choice]=leaderCards[0];
                        choice++;
                    }
                    if(card2.isSelected()){
                        chosen[choice]=leaderCards[1];
                        choice++;
                    }
                    if(card3.isSelected()){
                        chosen[choice]=leaderCards[2];
                        choice++;
                    }
                    if(card4.isSelected()){
                        chosen[choice]=leaderCards[3];
                        choice++;
                    }
                    if(choice==2){
                        if(mediaPlayer!=null)
                        mediaPlayer.stop();
                        confirmLeader.setDisable(true);
                        if(!card1.isSelected())
                        {
                            card1image.setImage(null);
                        }
                        if(!card2.isSelected())
                        {
                            card2image.setImage(null);
                        }if(!card3.isSelected())
                        {
                            card3image.setImage(null);
                        }if(!card4.isSelected())
                        {
                            card4image.setImage(null);
                        }
                        card1.setDisable(true);
                        card2.setDisable(true);
                        card3.setDisable(true);
                        card4.setDisable(true);

                        InitChooseLeaderCards action;
                        action = new InitChooseLeaderCards(chosen[0], chosen[1]);

                        clientConnection.send(action);
                    }
                } catch (IOException e) {
                    displayError(e.toString());
                }
            }else {
                try {
                    if(selected==1)
                        gui.guiError("You've picked just "+selected+" leader card!\nYou've got to pick two");
                    else{
                        gui.guiError("You've picked "+selected+" leader cards!\nYou've got to pick two");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * By giving a card id it returns the corresponding Image
     * @param cardId    the card id to show
     * @param front     true to get the front of the card, if false it returns the back of the card
     * @return          an image of the card, null if the cardId is 0 or negative. If it's a new Leader Card it's always shown the image of the 64th card
     */
    protected Image getImage(int cardId,boolean front){
        if(cardId<1)
            return null;
        if(cardId>64)
            cardId=64;
        String source;
        if(front)
        source = "Assets/Cards/F";
        else source = "Assets/Cards/B";
        String number = String.valueOf(cardId);
        return new Image(getClass().getResource(source+number+".png").toExternalForm());
    }

    /**
     * By giving a Marble it returns the corresponding image
     * @param           marble the marble to show
     * @return          the image of the marble
     */
    protected Image getImage(Marble marble){
        String source = "Assets/Game/";
        String color;
        if(marble.equals(Marble.WHITE)){
            color="white";
        }else if(marble.equals(Marble.BLUE)){
            color="blue";
        }else if(marble.equals(Marble.GREY)){
            color="grey";
        }else if(marble.equals(Marble.PURPLE)){
            color="purple";
        }else if(marble.equals(Marble.YELLOW)){
            color="yellow";
        }else
            color="red";
        return new Image(getClass().getResource(source+color+".png").toExternalForm());
    }

    /**
     * By giving a Resource it returns the corresponding image
     * @param resourceType  the resource to show
     * @return              the image of the resource OR null if the resource type is null
     */
    protected Image getImage(ResourceType resourceType){
        String source = "Assets/Game/";
        String resource;
        if(resourceType.equals(ResourceType.SERVANTS)){
            resource="servant";
        }else if(resourceType.equals(ResourceType.COINS)){
            resource="coin";
        }else if(resourceType.equals(ResourceType.STONES)){
            resource="stone";
        }else if(resourceType.equals(ResourceType.SHIELDS)){
            resource="shield";
        }else
            return null;
        return new Image(getClass().getResource(source+resource+".png").toExternalForm());
    }

    /**
     * By giving a SoloActionToken it returns the corresponding image
     * @param soloActionToken   the token to show
     * @return                  the image of the token
     */
    protected Image getImage(SoloActionToken soloActionToken){
        String source = "Assets/Game/";
        String token;
        if(soloActionToken.equals(SoloActionToken.DELETE2BLUE)){
            token="cerchio1.png";
        }else if(soloActionToken.equals(SoloActionToken.BLACKCROSSPLUS2)){
            token="cerchio5.png";
        }else if(soloActionToken.equals(SoloActionToken.BLACKCROSSSHUFFLE)){
            token="cerchio7.png";
        }else if(soloActionToken.equals(SoloActionToken.DELETE2GREEN)){
            token="cerchio2.png";
        }else if(soloActionToken.equals(SoloActionToken.DELETE2PURPLE)){
            token="cerchio3.png";
        }else if(soloActionToken.equals(SoloActionToken.DELETE2YELLOW)){
            token="cerchio4.png";
        }
        else
            return null;
        return new Image(getClass().getResource(source+token).toExternalForm());
    }

    /**
     * By giving a boolean it returns either the inkwell or the back of a token if the game is in singleplayer mode
     * @param inkwell    Inkwell picture if true, solo token back if fase
     * @return           the image of the inkwell/token
     */
    protected Image getImage(boolean inkwell){
        String source = "Assets/Game/";
        String token;
        if(inkwell)
            token="inkwell.png";
        else
            token="singletoken.png";
        return new Image(getClass().getResource(source+token).toExternalForm());
    }

    /**
     * By giving a Player it returns its faith marker: a green one if it's from the player, a red one if it is from other players
     * and a BlackCross token if its Lorenzo's
     * @param player    The player to get the token image from
     * @return          The token image
     */
    protected Image getImage(Player player){
        String source = "Assets/Game/";
        String token;
        if(player.getNickname().equals(this.clientConnection.getClient().getUserInteraction().getGame().getMyPlayer().getNickname()))
            token="crossActive.png";
        else if(player.getNickname().equals("Lorenzo il Magnifico"))
            token="croce.png";
        else token="cross.png";
        return new Image(getClass().getResource(source+token).toExternalForm());
    }

    /**
     * Used to get the Server Address and Server Port as input from the player.
     * @return An ArrayList of object.
     * - First element in the ArrayList -> String containing server address.
     * - Second element in the ArrayList -> Integer containing server port.
     */
    @Override
    public ArrayList<Object> initial() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(server.getText());
        objects.add(Integer.parseInt(port.getText()));
        client.startUp(objects);
        return objects;
    }

    /**
     * Does the actions given by the server
     * @param userInteraction   The userInteraction given
     * @param action            The UIAction
     */
    @Override
    public void nextAction(UserInteraction userInteraction, UIActions action){
        try {
        switch(action) {
            //Does nothing since the name selection screen is called only after establishing a connection
            case CHOOSE_NAME:{
                break;
            }
            //Calls the number selection screen
            case CHOOSE_NUMBER_OF_PLAYERS:{
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            playerNumberSelection();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
                break;
            //Opens the Leader Cards selection screen
            case INITIAL_CHOOSE_LEADER_CARDS:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            leaderCardSelection();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
                //Opens the Lobby screen
            case INITIAL_LOBBY:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lobby();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
                //Opens the initial resources screen
            case INITIAL_CHOOSE_RESOURCES:
                this.board = new GuiBoardController(client,clientConnection,gui);
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            board.getResources(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            }
            case PLAY_TURN:
                break;
                //Opens the win/lose screen
            case SINGLEPLAYER_END_LOST:
            case SINGLEPLAYER_END_WON:
            case FINAL_POINTS: {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            finalScore();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            }
            //Opens the main board
            case RECONNECTION:
            case DISPLAY_ACTION:{
                if(firstRun){
                    if(board==null)
                        this.board=new GuiBoardController(this.client,this.clientConnection,this.gui);
                    firstRun=false;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                board.board();
                                    firstRun=false;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }else{
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            board.refresh();
                        }
                    });
                }

            }
                break;
            //Opens an error message
            case DISPLAY_ERROR:
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(userInteraction.getMessage().getPlayerNickname().equals(board.myPlayer().getNickname()))
                            displayError(userInteraction.getMessage().getError());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
                break;
            //Shows the token got from the single player interaction
            case SINGLEPLAYER_TURN:{
                SoloActionToken token = this.client.getUserInteraction().getLorenzoToken();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                                gui.guiMessage("Token","You draw the following token",getImage(token));
                                board.refresh();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            }
            //Unused, it's for cli only
            case WAITING:
                break;
            default:
                displayError("Unexpected value: " + action);
        }
        } catch (Exception e) {
            displayError(e.toString());
        }
    }

    /**
     * Unused in Gui
     */
    public void waitingForPlayers() {

    }

    /**
     * Displays an error for the user to see.
     * @param message   the error message to display
     */
    public void displayError(String message){
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        gui.guiError(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}