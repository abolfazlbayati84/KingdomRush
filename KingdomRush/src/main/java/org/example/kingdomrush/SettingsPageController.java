package org.example.kingdomrush;

import Controller.MediaPlayerController;
import Controller.PlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPageController implements Initializable {

    @FXML
    private Button change_btn;

    @FXML
    private ImageView home_img;

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private ImageView music_img;

    @FXML
    private Label password_lbl;

    @FXML
    private TextField password_txf;

    @FXML
    private Label settings_lbl;

    @FXML
    private Label username_lbl;

    @FXML
    private TextField username_txf;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SettingsPageController.stage = stage;
    }

    @FXML
    void changeAction(ActionEvent event) throws IOException {
        PlayerController.getPlayerController().changeInformation(username_txf.getText(),password_txf.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void homeAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void musicAction(MouseEvent event) {

        if(MediaPlayerController.isIsPlaying()){
            MediaPlayerController.setIsPlaying(false);
            MediaPlayerController.getMediaPlayer().pause();
            String path = HelloApplication.class.getResource("mute.png").toExternalForm();
            Image image = new Image(path);
            music_img.setImage(image);
        }else{
            MediaPlayerController.setIsPlaying(true);
            MediaPlayerController.getMediaPlayer().play();
            String path = HelloApplication.class.getResource("volume.png").toExternalForm();
            Image image = new Image(path);
            music_img.setImage(image);
            new Thread(()->{
                MediaPlayerController.getMediaPlayer().setOnEndOfMedia(()->{
                    if(MediaPlayerController.isIsPlaying()){
                        MediaPlayerController.getMediaPlayer().seek(MediaPlayerController.getMediaPlayer().getStartTime());
                        MediaPlayerController.getMediaPlayer().play();
                    }
                });
            }).start();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_txf.setText(PlayerController.getPlayerController().getPlayer().getUsername());
        password_txf.setText(PlayerController.getPlayerController().getPlayer().getPassword());
        if(MediaPlayerController.isIsPlaying()){
            String path = HelloApplication.class.getResource("volume.png").toExternalForm();
            Image image = new Image(path);
            music_img.setImage(image);
        }else{
            String path = HelloApplication.class.getResource("mute.png").toExternalForm();
            Image image = new Image(path);
            music_img.setImage(image);
        }
    }
}
