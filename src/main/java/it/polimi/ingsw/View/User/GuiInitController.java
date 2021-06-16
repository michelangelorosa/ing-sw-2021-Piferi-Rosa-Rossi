package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.EndMarket;
import it.polimi.ingsw.Controller.Actions.EndTurn;
import it.polimi.ingsw.Controller.Actions.InitChooseLeaderCards;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.View.Client.Client;
import it.polimi.ingsw.View.Client.ClientConnection;
import it.polimi.ingsw.View.ReducedModel.Game;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.image.ImageView ;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;

/**
 * Handles the interactions between the User and the Gui application.
 * The interactions are sent to the GuiExceptionHandler for validation and visualization of the errors
 */
public class GuiInitController implements UserInterface {
    protected static Stage guiStage;
    protected Client client;
    protected MediaPlayer mediaPlayer;
    protected ClientConnection clientConnection;
    protected GuiBoardController board;
    @FXML private Button nameConfirm;
    @FXML private Label score;
    @FXML private TextField server;
    @FXML private TextField port;
    @FXML private Button connect;
    @FXML private Button confirmLeader;
    @FXML private TextField name;
    @FXML private CheckBox card1;
    @FXML private CheckBox card2;
    @FXML private CheckBox card3;
    @FXML private CheckBox card4;
    @FXML private ImageView card1image;
    @FXML private ImageView card2image;
    @FXML private ImageView card3image;
    @FXML private ImageView card4image;
    @FXML private ChoiceBox<String> numberPlayers;
    @FXML private Button playerNumberConfirm;
    @FXML private Button gameSettings;
    @FXML private Button readyToPlay;
    protected ClientExceptionHandler gui;
    protected ActionEvent event;

    /**
     * Constructor for Gui
     */

    public GuiInitController(Client client,ClientConnection clientConnection,ClientExceptionHandler clientExceptionHandler) {
        this.client=client;
        //Client.setUserInteraction(this);
        this.clientConnection=clientConnection;
        this.gui=clientExceptionHandler;
    }

    public static Stage getStage() {
        return guiStage;
    }
    public static void setStage(Stage stage) {
        guiStage=stage;
    }


    /**
     * Currently unused, it's a test for loading a music piece
     * @param event
     * @throws URISyntaxException if the path to music is formatted badly
     */
    public void finalScore(ActionEvent event) throws URISyntaxException {
        //Get final score
        int points =18;
        score.setText(Integer.toString(points));
        music(getClass().getResource("Assets/Music/Zigeunerweisen.mp3").toExternalForm());
    }



    /**
     * Plays Music with Media Player
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
     * Changes the scene from Server, Port selection to Name selection
     * @param event
     * @throws Exception
     */
    public void nameSelection(ActionEvent event) throws Exception{
        this.event=event;
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
     * Changes the scene from Name selection to Player Number selection
     * @throws Exception
     */
    public void playerNumberSelection() throws Exception{
        Parent playerNumberSelection;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/PlayerNumber.fxml"));
        loader.setController(this);
        playerNumberSelection = loader.load();

        Scene scene = new Scene(playerNumberSelection);
        //Getting the stage information
        //Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        Stage window = getStage();
        window.setScene(scene);

        ObservableList<String> playerNumbers = FXCollections.observableArrayList("1 Player","2 Players","3 Players","4 Players");
        this.numberPlayers.setItems(playerNumbers);
    }

    /**
     * Changes the scene from Name selection or PlayerNumber to the Lobby
     * @throws Exception
     */
    public void lobby() throws Exception{
        Parent lobby;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Lobby.fxml"));
        loader.setController(this);
        lobby = loader.load();

        Scene scene = new Scene(lobby);
        //Getting the stage information
        Stage window = getStage();
        window.setScene(scene);

        music(getClass().getResource("Assets/Music/Connection.mp3").toExternalForm());
    }

    /**
     * Gets the name from the name's Text Field and sends it to nameValidator for validation
     * @throws Exception
     */
    public void nameValidation(ActionEvent event) throws Exception{
        System.out.println("Name validation event: "+event);
        this.event=event;
        String player=name.getText();
        System.out.println(player);
        if(gui.nameValidator(player))
        {
            this.clientConnection.getClient().setUser(player);
            this.clientConnection.send(player);
        }
    }

    /**
     * Returns the number of players selected by the user in the player selection menu
     * @param event
     * @return              int: the number of players
     * @throws Exception
     */
    public void playerNumberValidation(ActionEvent event) throws Exception{
        this.event=event;
        System.out.println("Selected players: "+(numberPlayers.getSelectionModel().getSelectedIndex()+1));
        this.clientConnection.send(numberPlayers.getSelectionModel().getSelectedIndex()+1);
    }
    /**
     * Leader card selection screen
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
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(scene);
        card1image.setImage(card1);
        card2image.setImage(card2);
        card3image.setImage(card3);
        card4image.setImage(card4);
        leaderCardCheck(event,loader);
    }

    /**
     * Quits the game, invoked if the user clicks on "Exit" button
     * @param event
     */
    public void close(ActionEvent event){
        System.exit(0);
    }

    public void openSettings(ActionEvent event){
        System.out.println("Open settings");
    }

    public void setReady(ActionEvent event) throws IOException{
        this.event=event;
        readyToPlay.setDisable(true);
        gameSettings.setDisable(true);
        this.clientConnection.send(4);
    }

    /**
     * Checks that the user has picked two leader cards and enables the cards to be pickable into a checkbox
     * @param event
     * @param loader
     * @throws Exception
     */
    private void leaderCardCheck(ActionEvent event,FXMLLoader loader){
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

                        this.event=event;
                        clientConnection.send(action);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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
     * By giving a card id it returns the corresponding image
     * @param cardId the card id to show
     * @param front true to get the front of the card, if false it returns the back of the card
     * @return an image of the card, null if the cardId is 0 or negative. If it's a new Leader Card it's always shown the image of the 64th card
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
     * @param marble the marble to show
     * @return the image of the marble
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
     * @param resourceType the resource to show
     * @return the image of the resource OR null if the resource type is null
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
     * @param soloActionToken the token to show
     * @return the image of the token
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
     * Used to get the Server Address and Server Port as input from the player.
     *
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
        //Client.setUserInteraction(this);
        return objects;
    }

    @Override
    public void nextAction(UserInteraction userInteraction, UIActions action){
        try {
        switch(action) {
            case CHOOSE_NAME:{
                break;
            }
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
            case RECONNECTION:
                break;
            case PLAY_TURN:
                break;
            case FINAL_POINTS:
                break;
            case DISPLAY_ACTION:{
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            board.board();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
                break;
            case DISPLAY_ERROR:
            gui.guiError("Error");
                break;
            case WAITING:
                break;
            default:
                displayError("Unexpected value: " + action);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to display a message while the player waits for all the other players to join.
     */
    public void waitingForPlayers() {

    }

    /**
     * Displays an Alert Box for the user to see if the game is set to Gui Mode.
     * @param message the message to display
     */
    public void displayError(String message){
        try {
            gui.guiError(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}