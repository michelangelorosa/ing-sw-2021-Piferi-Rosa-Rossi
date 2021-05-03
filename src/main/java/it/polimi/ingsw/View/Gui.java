package it.polimi.ingsw.View;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Gui extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{

        ClientExceptionHandler gui= new ClientExceptionHandler();
        gui.visualType(false);
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Assets/Fxml/Intro.fxml"));
        //Creates a scene object
        Scene scene = new Scene(root);

        primaryStage.setTitle("Masters Of Renaissance");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
