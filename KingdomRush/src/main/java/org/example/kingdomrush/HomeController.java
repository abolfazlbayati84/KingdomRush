package org.example.kingdomrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.kingdomrush.Controller.MapController;
import org.example.kingdomrush.Model.Map.*;
import org.example.kingdomrush.Model.Player.Player;
import org.example.kingdomrush.Model.Tower.Archer;
import org.example.kingdomrush.Model.Tower.MortarBomb;
import org.example.kingdomrush.Model.Tower.Vizard;
import javafx.animation.PathTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Pane pane;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private Scene scene;
    private HBox hBox;
    private Label coinNumber;
    private Image raiderImage;
    private ImageView raiderImageView;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        HomeController.stage = stage;
    }

    public void addMapPic(String picAddress){
        pane = new Pane();
        scene = new Scene(pane, 830, 380);
        String path1=HelloApplication.class.getResource(picAddress).toExternalForm();
        image1=new Image(path1);
        imageView1 = new ImageView(image1);

        pane.getChildren().add(imageView1);
        stage.setTitle("Kingdom Rush");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
    public void addPopUp(int finalI,ArrayList<Coordinate> towerCoordinates,Map map){

        if(MapController.getMapController().getCoins()>=70) {
            String path2 = HelloApplication.class.getResource("popUp1.png").toExternalForm();
            String path3 = HelloApplication.class.getResource("popUp2.png").toExternalForm();
            String path4 = HelloApplication.class.getResource("popUp3.png").toExternalForm();
            image2 = new Image(path2);
            image3 = new Image(path3);
            image4 = new Image(path4);
            imageView2 = new ImageView(image2);
            imageView3 = new ImageView(image3);
            imageView4 = new ImageView(image4);
            hBox = new HBox();
            hBox.getChildren().add(imageView2);
            hBox.getChildren().add(imageView3);
            hBox.getChildren().add(imageView4);
            hBox.setLayoutX(towerCoordinates.get(finalI).getX() - 30);
            hBox.setLayoutY(towerCoordinates.get(finalI).getY() - 30);
            pane.getChildren().add(hBox);
        }
    }
    public void addTowerPic(String picAddress,int finalI,ArrayList<Coordinate> towerCoordinates,Map map){
        pane.getChildren().remove(hBox);
        String path5=HelloApplication.class.getResource(picAddress).toExternalForm();
        image5=new Image(path5);
        imageView5 = new ImageView(image5);
        imageView5.setLayoutX(towerCoordinates.get(finalI).getX()-30);
        imageView5.setLayoutY(towerCoordinates.get(finalI).getY()-30);
        imageView5.setFitHeight(50);
        imageView5.setFitWidth(70);
        pane.getChildren().add(imageView5);
        addNumberOfCoins();
    }
    public void addNumberOfCoins(){
        if(coinNumber == null){
            coinNumber = new Label();
        }
        coinNumber.setText("Coins: "+MapController.getMapController().getCoins());
    }
    public void managePopUp(){
        Map map = MapController.getMapController().getMap();
        for(int i=0 ; i<map.getTowerCoordinates().size();i++){
            Circle circle = new Circle(map.getTowerCoordinates().get(i).getX(),map.getTowerCoordinates().get(i).getY(),20);
            circle.setFill(new Color(0,0,1,0.05));
            pane.getChildren().add(circle);
            int finalI = i;
            circle.setOnMouseClicked(mouseEvent -> {
                addPopUp(finalI,map.getTowerCoordinates(),map);
                imageView2.setOnMouseClicked(event2 ->{
                    if(MapController.getMapController().getCoins()>=70){
                        MapController.getMapController().addTower(new Archer(new Coordinate(map.getTowerCoordinates().get(finalI).getX(),map.getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("archer.png",finalI,map.getTowerCoordinates(),map);
                    }
                });
                imageView3.setOnMouseClicked(event3 ->{
                    if(MapController.getMapController().getCoins()>=100){
                        MapController.getMapController().addTower(new Vizard(new Coordinate(map.getTowerCoordinates().get(finalI).getX(),map.getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("mage.png",finalI,map.getTowerCoordinates(),map);
                    }
                });
                imageView4.setOnMouseClicked(event4 ->{
                    if(MapController.getMapController().getCoins()>=125){
                        MapController.getMapController().addTower(new MortarBomb(new Coordinate(map.getTowerCoordinates().get(finalI).getX(),map.getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("artillery.png",finalI,map.getTowerCoordinates(),map);
                    }
                });
            });
        }

    }
    @FXML
    void firstLevelAction(MouseEvent event) throws IOException {
        addMapPic("first map.jpg");
        MapController.getMapController().setMap(FirstMap.getFirstMap());
        addNumberOfCoins();
        pane.getChildren().add(coinNumber);
        managePopUp();
        String path = HelloApplication.class.getResource("troll1.png").toExternalForm();
        String path3 = HelloApplication.class.getResource("troll2.png").toExternalForm();
        String path4 = HelloApplication.class.getResource("troll3.png").toExternalForm();
        raiderImage = new Image(path);
        raiderImageView = new ImageView(raiderImage);
        Image raiderImage3 = new Image(path3);
        Image raiderImage4 = new Image(path4);
        ImageView raiderImageView3 = new ImageView(raiderImage3);
        ImageView raiderImageView4 = new ImageView(raiderImage4);

        raiderImageView3.setFitHeight(30);
        raiderImageView3.setFitWidth(20);

        raiderImageView4.setFitWidth(20);
        raiderImageView4.setFitHeight(30);

        raiderImageView.setFitWidth(20);
        raiderImageView.setFitHeight(30);
        Group raider = new Group();
        pane.getChildren().add(raider);
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(FirstMap.getFirstMap().getPath().get(0).getX(),FirstMap.getFirstMap().getPath().get(0).getY()));
        boolean isFirstTime = true;
        for(Coordinate coordinate : FirstMap.getFirstMap().getPath()){
            if(!isFirstTime){
                path2.getElements().add(new LineTo(coordinate.getX(),coordinate.getY()));
            }
            isFirstTime = false;
        }
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path2);
        pathTransition.setDuration(Duration.seconds(60));
        pathTransition.setNode(raider);
        pathTransition.play();



        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),(ActionEvent event3) ->{
            raider.getChildren().setAll(raiderImageView);
        }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1000),(ActionEvent event3) ->{
            raider.getChildren().setAll(raiderImageView3);
        }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1500),(ActionEvent event3) ->{
            raider.getChildren().setAll(raiderImageView4);
        }
        ));
        t.play();
    }

    @FXML
    void fourthLevelAction(MouseEvent event) {

        scene.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();
            System.out.println("X = "+x+"\tY = "+y);
        });
    }

    @FXML
    void secondLevelAction(MouseEvent event) {
        addMapPic("second map.jpg");
        MapController.getMapController().setMap(SecondMap.getSecondMap());
        addNumberOfCoins();
        pane.getChildren().add(coinNumber);
        managePopUp();
    }

    @FXML
    void thirdLevelAction(MouseEvent event) {
        addMapPic("third map.jpg");
        MapController.getMapController().setMap(ThirdMap.getThirdMap());
        addNumberOfCoins();
        pane.getChildren().add(coinNumber);
        managePopUp();
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
