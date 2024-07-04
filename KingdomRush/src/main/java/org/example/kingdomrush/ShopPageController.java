package org.example.kingdomrush;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.kingdomrush.Controller.PlayerController;
import org.example.kingdomrush.Exceptions.NotEnoughDiamonds;
import org.example.kingdomrush.Model.Player.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopPageController implements Initializable {

    @FXML
    private ImageView bomb_img;

    @FXML
    private Label bomb_lbl;

    @FXML
    private Rectangle bomb_rect;

    @FXML
    private ImageView coin_img;

    @FXML
    private Label coin_lbl;

    @FXML
    private Rectangle coin_rect;

    @FXML
    private ImageView diamond_img;

    @FXML
    private Label diamond_lbl;

    @FXML
    private Label firstAction_lbl;

    @FXML
    private Label fourthAction_lbl;

    @FXML
    private ImageView heart_img;

    @FXML
    private Label heart_lbl;

    @FXML
    private Rectangle heart_rect;

    @FXML
    private ImageView home_img;

    @FXML
    private ImageView ice_img;

    @FXML
    private Label ice_lbl;

    @FXML
    private Rectangle ice_rect;

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private Label secondAction_lbl;

    @FXML
    private Label thirdAction_lbl;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ShopPageController.stage = stage;
    }

    @FXML
    void bombAction(MouseEvent event) {
        try {
            PlayerController.getPlayerController().addSpell("Bomb");
            diamond_lbl.setText(String.valueOf(Player.getPlayer().getDiamond()));
        } catch (NotEnoughDiamonds e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("You don't have enough diamonds.");
            alert.showAndWait();
        }
    }

    @FXML
    void coinAction(MouseEvent event) {
        try {
            PlayerController.getPlayerController().addSpell("Coin");
            diamond_lbl.setText(String.valueOf(Player.getPlayer().getDiamond()));
        } catch (NotEnoughDiamonds e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("You don't have enough diamonds.");
            alert.showAndWait();
        }
    }

    @FXML
    void heartAction(MouseEvent event) {
        try {
            PlayerController.getPlayerController().addSpell("Heart");
            diamond_lbl.setText(String.valueOf(Player.getPlayer().getDiamond()));
        } catch (NotEnoughDiamonds e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("You don't have enough diamonds.");
            alert.showAndWait();
        }
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
    void iceAction(MouseEvent event) {
        try {
            PlayerController.getPlayerController().addSpell("Freeze");
            diamond_lbl.setText(String.valueOf(Player.getPlayer().getDiamond()));
        } catch (NotEnoughDiamonds e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("You don't have enough diamonds.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diamond_lbl.setText(String.valueOf(Player.getPlayer().getDiamond()));
    }
}
