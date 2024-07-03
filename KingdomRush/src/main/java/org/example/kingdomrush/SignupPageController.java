package org.example.kingdomrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.kingdomrush.Controller.PlayerController;
import org.example.kingdomrush.Exceptions.UserNotFound;

import java.io.IOException;

public class SignupPageController {

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private Button login_btn;

    @FXML
    private Label login_lbl;

    @FXML
    private Label password_lbl;

    @FXML
    private TextField password_txf;

    @FXML
    private Label signUp_lbl;

    @FXML
    private Button signup_btn;

    @FXML
    private Label username_lbl;

    @FXML
    private TextField username_txf;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SignupPageController.stage = stage;
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        String username = username_txf.getText();
        String password = password_txf.getText();
        try {
            PlayerController.getPlayerController().signUp(username,password);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

}
