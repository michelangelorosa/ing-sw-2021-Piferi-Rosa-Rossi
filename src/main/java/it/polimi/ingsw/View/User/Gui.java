package it.polimi.ingsw.View.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;

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
        primaryStage.setResizable(false);
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
