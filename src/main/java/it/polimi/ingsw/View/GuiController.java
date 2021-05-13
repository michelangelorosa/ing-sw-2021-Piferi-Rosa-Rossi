package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.image.ImageView ;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Handles the interactions between the User and the Gui application.
 * The interactions are sent to the GuiExceptionHandler for validation and visualization of the errors
 */
public class GuiController {
    public MediaPlayer mediaPlayer;
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


    private ClientExceptionHandler gui = new ClientExceptionHandler();

    /**
     * Sets GuiExceptionHandler to Gui mode so that it generates graphical popup
     */
    public GuiController() {
        gui.visualType(false);
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
     * Quits the game, invoked if the user clicks on "Exit" button
     * @param event
     */
    public void close(ActionEvent event){
        System.exit(0);
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
     * Checks if the user has inserted good or bad values in the Text Fields for the connection to the server
     * @param event
     * @throws Exception
     */
    public void addressPort(ActionEvent event) throws Exception {
        int portNo;
        try{
            portNo=Integer.parseInt(port.getText());
            if(gui.addressValidator(server.getText()))
                if(gui.portValidator(portNo)){
                    nameSelection(event);
                }
        }catch (NumberFormatException e){
            gui.guiError("Port number not valid!");
        }
    }

    /**
     * Changes the scene from Server, Port selection to Name selection
     * @param event
     * @throws Exception
     */
    public void nameSelection(ActionEvent event) throws Exception{
        Parent nameSelection;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Name.fxml"));
        nameSelection = loader.load();
        Scene scene = new Scene(nameSelection);

        //Getting the stage information
        Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
        window.setScene(scene);
    }

    /**
     * Gets the name from the name's Text Field and sends it to nameValidator for validation
     * @throws Exception
     */
    public void nameValidation(ActionEvent event) throws Exception{
        String player=name.getText();
        if(gui.nameValidator(player))
        {
         System.out.println("NAME OK: " +player);
         leaderCardSelection(event);
        }
    }

    /**
     * Leader card selection screen
     * @param event
     * @throws Exception
     */
    public void leaderCardSelection(ActionEvent event) throws Exception{
        Image card1 = getImage(64,true);
        Image card2 = getImage(63,true);
        Image card3 = getImage(62,true);
        Image card4 = getImage(61,true);


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
     * Checks that the user has picked two leader cards and enables the cards to be pickable into a checkbox
     * @param event
     * @param loader
     * @throws Exception
     */
    private void leaderCardCheck(ActionEvent event,FXMLLoader loader){
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
        confirmLeader.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int selected=0;
                if(card1.isSelected())
                    selected++;
                if(card2.isSelected())
                    selected++;
                if(card3.isSelected())
                    selected++;
                if(card4.isSelected())
                    selected++;
                if(selected==2){
                    //goes to next screen
                }else {
                    try {
                        if(selected==1)
                            gui.guiError("You've picked just "+selected+" leader card!\nYou've got to pick two");
                        else
                        gui.guiError("You've picked "+selected+" leader cards!\nYou've got to pick two");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
    private Image getImage(int cardId,boolean front){
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
    private Image getImage(Marble marble){
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
        }else
            color="red";
        return new Image(getClass().getResource(source+color+".png").toExternalForm());
    }

    /**
     * By giving a Resource it returns the corresponding image
     * @param resourceType the resource to show
     * @return the image of the resource OR null if the resource type is null
     */
    private Image getImage(ResourceType resourceType){
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
    private Image getImage(SoloActionToken soloActionToken){
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
}