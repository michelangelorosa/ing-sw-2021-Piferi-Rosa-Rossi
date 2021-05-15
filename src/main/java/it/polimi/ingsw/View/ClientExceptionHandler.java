package it.polimi.ingsw.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ClientExceptionHandler {
    private boolean cli;
    private boolean gui;
    private Stage errorBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Button button;

    /**
     * Sets the visualization type for the error generated Client-side
     * @param isCli defines if the error has to be generated in a Gui enviroment (false) or in a Cli enviroment (true).
     *              Has to be set at the beginning, after the selection of cli or gui.
     */
    public void visualType(boolean isCli){
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
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        if (address.matches(PATTERN)||address.equals("localhost")){
            System.out.println("Server ok");
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
        errorBox = new Stage();
        errorBox.initModality(Modality.APPLICATION_MODAL);
        errorBox.setTitle("Error");

        Scene scene = new Scene(errorMessage);
        errorLabel.setText(message);
        errorBox.setScene(scene);
        button.setOnAction(e->errorBox.close());
        errorBox.showAndWait();
    }

    /**
     * Displays an Alert Box for the user to see if the game is set to Cli Mode.
     * @param s the message to display
     */
    public void cliError(String s) {
        ArrayList<String> error = new ArrayList<>();
        String b = ANSIColors.BACK_BRIGHT_WHITE;
        String fw = ANSIColors.FRONT_BRIGHT_WHITE;
        String f = "\u001B[38;5;160m";
        String br = "\u001B[48;5;160m";
        String r = ANSIColors.RESET;

        String line = br + fw + "╔";
        String line2 = br + fw + "╚";
        for(int i = 0; i < s.length() + 6; i++) {
            line += "═";
            line2 += "═";
        }
        line += "╗" + r;
        line2 += "╝" + r;

        error.add(line);
        error.add(br + fw + "║" + b + f + ANSIColors.BOLD + " ! " + s + " ! " + r + br + fw + "║" + r);
        error.add(line2);

        for(int i = 0; i < 30; i++)
            System.out.println();

        for(String string : error)
            System.out.println(string);
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
