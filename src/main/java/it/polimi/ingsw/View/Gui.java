package it.polimi.ingsw.View;
import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.PayResourceBuyCard;
import it.polimi.ingsw.Controller.Actions.PayResourceProduction;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;
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
import java.util.concurrent.CountDownLatch;


import java.io.File;
import java.util.ArrayList;

public class Gui extends Application{
    public static final CountDownLatch latch = new CountDownLatch(1);
    public static Gui gui = null;
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
        //primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static Gui waitForStartUp() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gui;
    }

    public static void setStartUpTest(Gui startUpTest0) {
        gui = startUpTest0;
        latch.countDown();
    }

    public Gui(){
        setStartUpTest(this);
    }

}
