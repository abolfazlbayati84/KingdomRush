package org.example.kingdomrush;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.kingdomrush.Model.Map.FirstMap;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstMapController implements Initializable {

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private ImageView map_img;
    private static Stage stage;

    private static Scene scene;
    private static FirstMap firstMap;

    public static FirstMap getFirstMap() {
        return firstMap;
    }

    public static void setFirstMap(FirstMap firstMap) {
        FirstMapController.firstMap = firstMap;
    }

    public static void setScene(Scene scene) {
        FirstMapController.scene = scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FirstMapController.stage = stage;
    }
    public static void setScene(){
        scene.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();
            System.out.println("X = "+x+"\tY = "+y);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        scene.setOnMouseClicked(mouseEvent -> {
//            double x = mouseEvent.getSceneX();
//            double y = mouseEvent.getSceneY();
//            System.out.println("X = "+x+"\tY = "+y);
//        });

        firstMap = FirstMap.getFirstMap();
    }
}
