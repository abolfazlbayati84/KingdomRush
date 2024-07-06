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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.kingdomrush.Controller.MapController;
import org.example.kingdomrush.Model.Map.*;
import org.example.kingdomrush.Model.Player.Player;
import org.example.kingdomrush.Model.Tower.Archer;
import org.example.kingdomrush.Model.Tower.MortarBomb;
import org.example.kingdomrush.Model.Tower.Vizard;

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

        if(map.getStartingCoins()>=70) {
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
        addNumberOfCoins(map);
    }
    public void addNumberOfCoins(Map map){
        if(coinNumber == null){
            coinNumber = new Label();
        }
        if(map instanceof FirstMap){
            coinNumber.setText("Coins: "+FirstMap.getFirstMap().getStartingCoins());

        }else if(map instanceof SecondMap){
            coinNumber.setText("Coins: "+SecondMap.getSecondMap().getStartingCoins());

        }else if(map instanceof ThirdMap){
            coinNumber.setText("Coins: "+ThirdMap.getThirdMap().getStartingCoins());

        }
    }
    @FXML
    void firstLevelAction(MouseEvent event) throws IOException {
        addMapPic("first map.jpg");
        addNumberOfCoins(FirstMap.getFirstMap());
        pane.getChildren().add(coinNumber);
        ArrayList<Coordinate> towerCoordinates = FirstMap.getFirstMap().getTowerCoordinates();
        for(int i=0 ; i<FirstMap.getFirstMap().getTowerCoordinates().size();i++){
            Circle circle = new Circle(FirstMap.getFirstMap().getTowerCoordinates().get(i).getX(),FirstMap.getFirstMap().getTowerCoordinates().get(i).getY(),20);
            circle.setFill(new Color(0,0,1,0.05));
            pane.getChildren().add(circle);
            int finalI = i;
            circle.setOnMouseClicked(mouseEvent -> {
                addPopUp(finalI,towerCoordinates,FirstMap.getFirstMap());
                imageView2.setOnMouseClicked(event2 ->{
                    if(FirstMap.getFirstMap().getStartingCoins()>=70){
                        MapController.getMapController().addTower(FirstMap.getFirstMap(),new Archer(new Coordinate(FirstMap.getFirstMap().getTowerCoordinates().get(finalI).getX(),FirstMap.getFirstMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("archer.png",finalI,towerCoordinates,FirstMap.getFirstMap());
                    }
                });
                imageView3.setOnMouseClicked(event3 ->{
                    if(FirstMap.getFirstMap().getStartingCoins()>=100){
                        MapController.getMapController().addTower(FirstMap.getFirstMap(),new Vizard(new Coordinate(FirstMap.getFirstMap().getTowerCoordinates().get(finalI).getX(),FirstMap.getFirstMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("mage.png",finalI,towerCoordinates,FirstMap.getFirstMap());
                    }
                });
                imageView4.setOnMouseClicked(event4 ->{
                    if(FirstMap.getFirstMap().getStartingCoins()>=125){
                        MapController.getMapController().addTower(FirstMap.getFirstMap(),new MortarBomb(new Coordinate(FirstMap.getFirstMap().getTowerCoordinates().get(finalI).getX(),FirstMap.getFirstMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("artillery.png",finalI,towerCoordinates,FirstMap.getFirstMap());
                    }
                });
            });
        }

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
        ArrayList<Coordinate> towerCoordinates = SecondMap.getSecondMap().getTowerCoordinates();
        addMapPic("second map.jpg");
        addNumberOfCoins(SecondMap.getSecondMap());
        pane.getChildren().add(coinNumber);
        for(int i = 0; i< SecondMap.getSecondMap().getTowerCoordinates().size(); i++){
            Circle circle = new Circle(SecondMap.getSecondMap().getTowerCoordinates().get(i).getX(),SecondMap.getSecondMap().getTowerCoordinates().get(i).getY(),20);
            circle.setFill(new Color(0,0,1,0.05));
            pane.getChildren().add(circle);
            int finalI=i;
            circle.setOnMouseClicked(mouseEvent -> {
                addPopUp(finalI,towerCoordinates,SecondMap.getSecondMap());
                imageView2.setOnMouseClicked(event2 ->{
                    if(SecondMap.getSecondMap().getStartingCoins()>=70){
                        MapController.getMapController().addTower(SecondMap.getSecondMap(),new Archer(new Coordinate(SecondMap.getSecondMap().getTowerCoordinates().get(finalI).getX(),SecondMap.getSecondMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("archer.png",finalI,towerCoordinates,SecondMap.getSecondMap());
                    }
                });
                imageView3.setOnMouseClicked(event3 ->{
                    if(SecondMap.getSecondMap().getStartingCoins()>=100){
                        MapController.getMapController().addTower(SecondMap.getSecondMap(),new Vizard(new Coordinate(SecondMap.getSecondMap().getTowerCoordinates().get(finalI).getX(),SecondMap.getSecondMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("mage.png",finalI,towerCoordinates,SecondMap.getSecondMap());
                    }
                });
                imageView4.setOnMouseClicked(event4 ->{
                    if(SecondMap.getSecondMap().getStartingCoins()>=125){
                        MapController.getMapController().addTower(SecondMap.getSecondMap(),new MortarBomb(new Coordinate(SecondMap.getSecondMap().getTowerCoordinates().get(finalI).getX(),SecondMap.getSecondMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("artillery.png",finalI,towerCoordinates,SecondMap.getSecondMap());
                    }
                });
            });
        }
    }

    @FXML
    void thirdLevelAction(MouseEvent event) {
        addMapPic("third map.jpg");
        addNumberOfCoins(ThirdMap.getThirdMap());
        pane.getChildren().add(coinNumber);
        ArrayList<Coordinate> towerCoordinates = ThirdMap.getThirdMap().getTowerCoordinates();
        for(int i = 0; i< ThirdMap.getThirdMap().getTowerCoordinates().size(); i++){
            Circle circle = new Circle(ThirdMap.getThirdMap().getTowerCoordinates().get(i).getX(),ThirdMap.getThirdMap().getTowerCoordinates().get(i).getY(),20);
            circle.setFill(new Color(0,0,1,0.05));
            pane.getChildren().add(circle);
            int finalI = i;
            circle.setOnMouseClicked(mouseEvent -> {
                addPopUp(finalI,towerCoordinates,ThirdMap.getThirdMap());
                imageView2.setOnMouseClicked(event2 ->{
                    if(ThirdMap.getThirdMap().getStartingCoins()>=70){
                        MapController.getMapController().addTower(ThirdMap.getThirdMap(),new Archer(new Coordinate(ThirdMap.getThirdMap().getTowerCoordinates().get(finalI).getX(),ThirdMap.getThirdMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("archer.png",finalI,towerCoordinates,ThirdMap.getThirdMap());
                    }
                });
                imageView3.setOnMouseClicked(event3 ->{
                    if(ThirdMap.getThirdMap().getStartingCoins()>=100){
                        MapController.getMapController().addTower(ThirdMap.getThirdMap(),new Vizard(new Coordinate(ThirdMap.getThirdMap().getTowerCoordinates().get(finalI).getX(),ThirdMap.getThirdMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("mage.png",finalI,towerCoordinates,ThirdMap.getThirdMap());
                    }
                });
                imageView4.setOnMouseClicked(event4 ->{
                    if(ThirdMap.getThirdMap().getStartingCoins()>=125){
                        MapController.getMapController().addTower(ThirdMap.getThirdMap(),new MortarBomb(new Coordinate(ThirdMap.getThirdMap().getTowerCoordinates().get(finalI).getX(),ThirdMap.getThirdMap().getTowerCoordinates().get(finalI).getY())));
                        addTowerPic("artillery.png",finalI,towerCoordinates,ThirdMap.getThirdMap());
                    }
                });
            });
        }
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
