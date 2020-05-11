package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class music {

    //TODO: changer musique

    private Button mute;
    private MediaPlayer mediaPlayer;
    private String song;
    private boolean muteon=false;

    public music(){
        song = "home.wav";
        Media h = new Media(Paths.get(song).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.44);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mute = new Button();
        mute.setGraphic(new ImageView("/Images/unmute.png"));
        mute.setPrefHeight(10);
        mute.setPrefWidth(10);
        setMute(mute);
    }
    private void setMute(Button mute){
        mute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!muteon){
                    mediaPlayer.setVolume(0);
                    muteon=true;
                    mute.setGraphic(new ImageView("/Images/mute.png"));
                }
                else{
                    mediaPlayer.setVolume(0.44);
                    muteon=false;
                    mute.setGraphic(new ImageView("/Images/unmute.png"));
                }
            }
        });
    }

    public Button getMute() {
        return mute;
    }
}
