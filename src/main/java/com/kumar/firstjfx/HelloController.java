package com.kumar.firstjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class HelloController {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblDuration;

    @FXML
    private Slider slider;

    private MediaPlayer mediaPlayer;

    private boolean isPlayer = false;

    @FXML
    void btnPlay(MouseEvent event) {
        if (!isPlayer) {
            btnPlay.setText("Pause");
            mediaPlayer.play();
            isPlayer=true;
        }
        else{
            btnPlay.setText("Play");
            mediaPlayer.pause();
            isPlayer=false;
        }
    }

    @FXML
    void btnStop(ActionEvent event) {
        btnPlay.setText("Play");
        mediaPlayer.stop();
        isPlayer=false;
    }

    @FXML
    void selectMedia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
               slider.setValue(newValue.toSeconds());
               lblDuration.setText("Duration: " + (int)slider.getValue() + " / " + (int)media.getDuration().toSeconds());
            }));

            mediaPlayer.setOnReady(() ->{
                Duration totalDuration = media.getDuration();
                slider.setValue(totalDuration.toSeconds());
                lblDuration.setText("Duration: 00 / " + (int)media.getDuration().toSeconds());

            });

            Scene scene = mediaView.getScene();
            mediaView.fitHeightProperty().bind(scene.widthProperty());
            mediaView.fitHeightProperty().bind(scene.heightProperty());
           // mediaPlayer.setAutoPlay(true);

        }
    }

    @FXML
    private void sliderPressed(MouseEvent event){
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }
}
