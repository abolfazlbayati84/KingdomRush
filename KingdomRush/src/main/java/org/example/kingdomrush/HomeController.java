package org.example.kingdomrush;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.kingdomrush.Model.Map.FirstMap;
import org.example.kingdomrush.Model.Map.SecondMap;
import org.example.kingdomrush.Model.Player.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView diamondImage;

    @FXML
    private Label diamondLabel;

    @FXML
    private Circle firstCircle;

    @FXML
    private Label firstLabel;

    @FXML
    private Circle fourthCircle;

    @FXML
    private Label fourthLabel;

    @FXML
    private AnchorPane initialAnchor;

    @FXML
    private Circle secondCircle;

    @FXML
    private Label secondLabel;

    @FXML
    private ImageView settingsImage;

    @FXML
    private ImageView shopImage;

    @FXML
    private Circle thirdCircle;

    @FXML
    private Label thirdLabel;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        HomeController.stage = stage;
    }

    @FXML
    void firstLevelAction(MouseEvent event) throws IOException {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 830, 380);
        String path1=HelloApplication.class.getResource("first map.jpg").toExternalForm();
        Image image1=new Image(path1);
        ImageView imageView1 = new ImageView(image1);

        pane.getChildren().add(imageView1);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        scene.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();
            System.out.println("X = "+x+"\tY = "+y);
        });

        for(int i=0 ; i<FirstMap.getFirstMap().getTowerCoordinates().size();i++){
            Circle circle = new Circle(FirstMap.getFirstMap().getTowerCoordinates().get(i).getX(),FirstMap.getFirstMap().getTowerCoordinates().get(i).getY(),20);
            circle.setFill(new Color(0,0,1,0.05));
            pane.getChildren().add(circle);
            circle.setOnMouseClicked(mouseEvent -> {
                System.out.println("TOUCHED");
            });
        }

    }

    @FXML
    void fourthLevelAction(MouseEvent event) {

    }

    @FXML
    void secondLevelAction(MouseEvent event) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 830, 380);
        String path1=HelloApplication.class.getResource("second map.jpg").toExternalForm();
        Image image1=new Image(path1);
        ImageView imageView1 = new ImageView(image1);

        pane.getChildren().add(imageView1);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        scene.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();
            System.out.println("X = "+x+"\tY = "+y);
        });

        for(int i = 0; i< SecondMap.getSecondMap().getTowerCoordinates().size(); i++){
            Circle circle = new Circle(SecondMap.getSecondMap().getTowerCoordinates().get(i).getX(),SecondMap.getSecondMap().getTowerCoordinates().get(i).getY(),20);
            circle.setFill(new Color(0,0,1,0.05));
            pane.getChildren().add(circle);
            circle.setOnMouseClicked(mouseEvent -> {
                System.out.println("TOUCHED");
            });
        }
    }

    @FXML
    void thirdLevelAction(MouseEvent event) {

    }

    @FXML
    void settingsAction(MouseEvent event) {

    }

    @FXML
    void shopAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("shop-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diamondLabel.setText(String.valueOf(Player.getPlayer().getDiamond()));
    }
}
