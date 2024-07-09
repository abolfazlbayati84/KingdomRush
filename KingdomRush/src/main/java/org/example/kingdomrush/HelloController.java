package org.example.kingdomrush;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private ImageView exitImage;

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private ImageView logoImage;

    @FXML
    private ImageView startImage;
    static private Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        HelloController.stage = stage;
    }

    @FXML
    void exitAction(MouseEvent event) {

        System.exit(0);
    }

    @FXML
    void startAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

}
