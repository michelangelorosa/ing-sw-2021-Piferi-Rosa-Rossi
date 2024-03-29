package it.polimi.ingsw.View.User;

import it.polimi.ingsw.View.Utility.ANSIColors;
import it.polimi.ingsw.View.Utility.ANSIfont;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Handles some validation and all the error messages in the Gui
 */
public class ClientExceptionHandler {
    private boolean cli;
    private boolean gui;
    private Stage errorBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Button button;
    private Font Baskerville;
    private Font Dominican;
    @FXML private ImageView boxedImage;
    @FXML private Text messageTitle;
    /**
     * Sets the visualization type for the error generated Client-side
     * @param isCli defines if the error has to be generated in a Gui enviroment (false) or in a Cli enviroment (true).
     *              Has to be set at the beginning, after the selection of cli or gui.
     */
    public void visualType(boolean isCli){
        Baskerville=Font.loadFonts(getClass().getResource("Assets/Fonts/Baskerville.ttc").toExternalForm(),20)[0];
        Dominican=Font.loadFont(getClass().getResource("Assets/Fonts/Dominican.ttf").toExternalForm(),38);
        if(isCli)
            cli=true;
        else
            gui=true;
    }

    /**
     * Checks if the client is trying to connect to an illegal address
     * @param address the address the user has inserted
     */
    public boolean addressValidator(String address) throws Exception {
        String IPv4 = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        String URL = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        if (address.matches(IPv4)||address.equals("localhost")||address.matches(URL)){
            return true;
        }
        String addressError ="Please enter a valid ipv4 address!";
        if(gui)
            guiError(addressError);
        if(cli)
            cliError(addressError);

        return false;
    }

    /**
     * Checks if the user wants to connect to a reserved port or an illegal port
     * @param port the port the user is trying to establish a connection to
     */
    public boolean portValidator(int port) throws Exception {
    String portError ="Please enter a valid port number!";
    if(port<=1024||port>65535) {
        if (gui)
            guiError(portError);
        else if (cli)
            cliError(portError);

        return false;
    }
    return true;
    }

    /**
     * Displays an Alert Box for the user to see if the game is set to Gui Mode.
     * @param message the message to display
     */
    public void guiError(String message) throws Exception{
        Parent errorMessage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Error.fxml"));
        loader.setController(this);
        errorMessage = loader.load();
        Scene scene = new Scene(errorMessage);

        errorBox = new Stage();
        errorBox.initModality(Modality.APPLICATION_MODAL);
        errorBox.setTitle("Error");

        boxedImage.setImage(new Image(getClass().getResource("Assets/Game/singletoken.png").toExternalForm()));
        errorLabel.setText(message);
        errorBox.setScene(scene);
        button.setOnAction(e->errorBox.close());
        errorBox.showAndWait();
    }

    /**
     * Displays an Alert Box for the user to see if the game is set to Gui Mode.
     */
    public void guiMessage(String title, String text, Image image) throws Exception{
        Parent guiMessage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Assets/Fxml/Error.fxml"));
        loader.setController(this);
        guiMessage = loader.load();
        errorBox = new Stage();
        errorBox.initModality(Modality.APPLICATION_MODAL);

        errorBox.setTitle(title);
        messageTitle.setText(title);
        errorLabel.setText(text);
        boxedImage.setImage(image);

        Scene scene = new Scene(guiMessage);

        errorBox.setScene(scene);
        button.setOnAction(e->errorBox.close());
        errorBox.showAndWait();
    }

    /**
     * Displays an Alert Box for the user to see if the game is set to Cli Mode.
     * @param s the message to display
     */
    public void cliError(String s) {
        System.out.println(ANSIfont.BOLD + "\u001B[38;5;160m" + s + ANSIColors.RESET);
    }

    /**
     * Checks if the user has provided a valid name
     * @param name the string the user wants to set as a username
     */
    public boolean nameValidator(String name) throws Exception {
        if(name.isEmpty()||name.isBlank()||name.length()>16){
            String nameErr = "Please insert a valid username with maximum 16 characters!";
            System.out.println(nameErr);
            if(gui)
                guiError(nameErr);
            if(cli)
                cliError(nameErr);
            return false;
        }
        return true;
    }

}
