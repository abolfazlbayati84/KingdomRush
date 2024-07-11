package org.example.kingdomrush;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WinPageController {

    @FXML
    private ImageView home_img;

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private Label win_lbl;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        WinPageController.stage = stage;
    }

    @FXML
    void homeAction(MouseEvent event) {

    }

}
