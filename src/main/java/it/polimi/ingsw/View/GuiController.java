package it.polimi.ingsw.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class GuiController {
    MediaPlayer mediaPlayer;
    @FXML
    private Label score;
    public void finalScore(ActionEvent event) throws URISyntaxException {
        //Get final score
        int points =18;
        score.setText(Integer.toString(points));
        music(getClass().getResource("Assets/Music/Zigeunerweisen.mp3").toExternalForm());
    }
    public void close(ActionEvent event){
        System.exit(0);
    }
    public void music(String string){
        Media media = null;
        System.out.println(string);
        media = new Media(string);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

    }
}
