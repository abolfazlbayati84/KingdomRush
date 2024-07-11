package org.example.kingdomrush;

import Controller.MediaPlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import Model.Player.Player;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HelloController.setStage(stage);
        SignupPageController.setStage(stage);
        LoginPageController.setStage(stage);
        HomeController.setStage(stage);
        ShopPageController.setStage(stage);
        SettingsPageController.setStage(stage);
        WinPageController.setStage(stage);
        LostPageController.setStage(stage);

        MediaPlayerController.getMediaPlayerController();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}