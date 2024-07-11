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
import Controller.PlayerController;
import Exceptions.UserNotFound;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private Button signUp_btn;

    @FXML
    private Label password_lbl;

    @FXML
    private TextField password_txf;

    @FXML
    private Button login_btn;

    @FXML
    private Label signUp_lbl;

    @FXML
    private Label username_lbl;

    @FXML
    private TextField username_txf;
    static private Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        LoginPageController.stage = stage;
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        boolean isLoggedIn=false;
        try {
            PlayerController.getPlayerController().login(username_txf.getText(),password_txf.getText());
            isLoggedIn = true;
        } catch (UserNotFound e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Couldn't find the user");
            alert.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(isLoggedIn){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 400);
            stage.setTitle("Kingdom Rush");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

}
