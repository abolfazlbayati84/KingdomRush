package org.example.kingdomrush;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.kingdomrush.Controller.*;
import org.example.kingdomrush.Model.Map.*;
import org.example.kingdomrush.Model.Player.Player;
import org.example.kingdomrush.Model.Raiders.Raider;
import org.example.kingdomrush.Model.Tower.Archer;
import org.example.kingdomrush.Model.Tower.MortarBomb;
import org.example.kingdomrush.Model.Tower.Tower;
import org.example.kingdomrush.Model.Tower.Vizard;
import javafx.animation.PathTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
    private static Label coinNumber;
    private Image raiderImage;
    private ImageView raiderImageView;
    private Label wave_lbl;
    private Button nextWave_btn;
    private ImageView levelImage;
    private HBox upgradeHbox;
    private java.util.Map<Tower,ImageView> levelMap;
    private java.util.Map<Tower,ImageView> towerImageViewMap;
    private int raiderCounter;
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        HomeController.stage = stage;
    }

    public static void setCoinNumber(Label coinNumber) {
        HomeController.coinNumber = coinNumber;
    }

    public static Label getCoinNumber() {
        return coinNumber;
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
    public void upgradeHandle(Tower tower){
        pane.getChildren().remove(hBox);
        if(MapController.getMapController().upgradeTower(tower)){
            int level = tower.getLevel();
            if(level == 2){
                String path = HelloApplication.class.getResource("two.png").toExternalForm();
                Image image = new Image(path);
                levelImage = new ImageView(image);
                levelMap.put(tower,levelImage);
            } else if (level == 3) {
                String path = HelloApplication.class.getResource("three.png").toExternalForm();
                Image image = new Image(path);
                levelImage = new ImageView(image);
                levelMap.replace(tower,levelImage);
            } else if (level == 4) {
                String path = HelloApplication.class.getResource("four.png").toExternalForm();
                Image image = new Image(path);
                levelImage = new ImageView(image);
                levelMap.replace(tower,levelImage);
            }
            levelImage.setFitHeight(20);
            levelImage.setFitWidth(20);
            levelImage.setLayoutX(tower.getCoordinate().getX()+30);
            levelImage.setLayoutY(tower.getCoordinate().getY()-30);
            pane.getChildren().add(levelImage);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("You can upgrade the towers up to your level.");
            alert.showAndWait();
        }
    }
    public void destroyTower(ImageView towerImageView,Tower tower){
        MapController.getMapController().destroyTower(tower);
        pane.getChildren().remove(hBox);
        ImageView imageView2 = towerImageViewMap.get(tower);
        pane.getChildren().remove(imageView2);
        if(tower.getLevel()>1){
            ImageView imageView = levelMap.get(tower);
            pane.getChildren().remove(imageView);
        }
    }
    public void addUpgradePopUp(int finalI,ArrayList<Coordinate> towerCoordinates,Tower tower,ImageView towerImageView){
        if(tower.getLevel()!=4){
            String upgradePath = HelloApplication.class.getResource("upgrade (2).png").toExternalForm();
            String destroyPath = HelloApplication.class.getResource("destroy.png").toExternalForm();

            Image upgradeImage = new Image(upgradePath);
            Image destroyImage = new Image(destroyPath);

            ImageView upgradeImageView = new ImageView(upgradeImage);
            ImageView destroyImageView = new ImageView(destroyImage);

            upgradeImageView.setOnMouseClicked(mouseEvent -> {
                upgradeHandle(tower);
            });
            destroyImageView.setOnMouseClicked(mouseEvent -> {
                destroyTower(towerImageView,tower);
            });

            upgradeImageView.setFitWidth(20);
            upgradeImageView.setFitHeight(20);
            destroyImageView.setFitWidth(20);
            destroyImageView.setFitHeight(20);

            hBox = new HBox();
            hBox.getChildren().add(upgradeImageView);
            hBox.getChildren().add(destroyImageView);

            hBox.setLayoutX(towerCoordinates.get(finalI).getX() - 30);
            hBox.setLayoutY(towerCoordinates.get(finalI).getY() - 30);

            pane.getChildren().add(hBox);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("The tower can't be upgraded anymore.");
            alert.showAndWait();
        }
    }
    public void addTowerPic(String picAddress,int finalI,ArrayList<Coordinate> towerCoordinates,Tower tower){
        pane.getChildren().remove(hBox);
        String path5=HelloApplication.class.getResource(picAddress).toExternalForm();
        image5=new Image(path5);
        imageView5 = new ImageView(image5);
        imageView5.setLayoutX(towerCoordinates.get(finalI).getX()-30);
        imageView5.setLayoutY(towerCoordinates.get(finalI).getY()-30);
        imageView5.setFitHeight(50);
        imageView5.setFitWidth(70);
        towerImageViewMap.put(tower,imageView5);
        imageView5.setOnMouseClicked(mouseEvent -> {
            addUpgradePopUp(finalI,towerCoordinates,tower,imageView5);
        });
        pane.getChildren().add(imageView5);
        addNumberOfCoins();
    }
    public void addNumberOfCoins(){
        if(coinNumber == null){
            coinNumber = new Label();
        }
        String path = HelloApplication.class.getResource("dollar-icon-png-3537.png").toExternalForm();
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setLayoutY(2);
        pane.getChildren().add(imageView);
        coinNumber.setText(" "+MapController.getMapController().getCoins());
        coinNumber.setTextFill(Color.WHITE);
        Font font = new Font("Arial",24);
        coinNumber.setFont(font);
        coinNumber.setLayoutX(22);
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
                        Tower tower = new Archer(new Coordinate(map.getTowerCoordinates().get(finalI).getX(),map.getTowerCoordinates().get(finalI).getY()));
                        MapController.getMapController().addTower(tower,pane);
                        addTowerPic("archer.png",finalI,map.getTowerCoordinates(),tower);
                    }
                });
                imageView3.setOnMouseClicked(event3 ->{
                    if(MapController.getMapController().getCoins()>=100){
                        Tower tower = new Vizard(new Coordinate(map.getTowerCoordinates().get(finalI).getX(),map.getTowerCoordinates().get(finalI).getY()));
                        MapController.getMapController().addTower(tower,pane);
                        addTowerPic("mage.png",finalI,map.getTowerCoordinates(),tower);
                    }
                });
                imageView4.setOnMouseClicked(event4 ->{
                    if(MapController.getMapController().getCoins()>=125){
                        Tower tower = new MortarBomb(new Coordinate(map.getTowerCoordinates().get(finalI).getX(),map.getTowerCoordinates().get(finalI).getY()));
                        MapController.getMapController().addTower(tower,pane);
                        addTowerPic("artillery.png",finalI,map.getTowerCoordinates(),tower);
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
        levelMap = new HashMap<>();
        towerImageViewMap = new HashMap<>();
        raiderCounter = 0;
        managePopUp();

        addNextButton();

        AtomicInteger waveIndex = new AtomicInteger(0);
        AtomicBoolean nextWave = new AtomicBoolean(false);
        addWavePic();
        updateWave(waveIndex);

        nextWave_btn.setOnMouseClicked(mouseEvent -> {
            nextWave.set(true);
            addNextButton();
        });

        new Thread(() -> {
            while (waveIndex.get() < 3) {
                Platform.runLater(() -> {
                    if (nextWave.get() && waveIndex.get() < 3) {
                        Wave wave = FirstMap.getFirstMap().getWaves().get(waveIndex.get());
                        addRaiderToMap(0,wave);
                        waveIndex.incrementAndGet();
                        updateWave(waveIndex);
                        nextWave.set(false);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    void fourthLevelAction(MouseEvent event) {

        scene.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();
            System.out.println("X = "+x+"\tY = "+y);
        });
    }

    public void updateWave(AtomicInteger waveIndex){
        if(wave_lbl == null){
            wave_lbl = new Label();
            pane.getChildren().add(wave_lbl);
        }
        wave_lbl.setText("Waves: "+waveIndex+"/3");
        Font font = new Font("Arial",24);
        wave_lbl.setFont(font);
        wave_lbl.setTextFill(Color.WHITE);
        wave_lbl.setLayoutY(25);
        wave_lbl.setLayoutX(27);
    }
    public void addWavePic(){
        String path = HelloApplication.class.getResource("skull.png").toExternalForm();
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setLayoutY(25);
        pane.getChildren().add(imageView);
    }
    public void setPathForRaiders(Raider raider){
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(MapController.getMapController().getMap().getPath().get(0).getX(), MapController.getMapController().getMap().getPath().get(0).getY()));
        boolean isFirstTime = true;
        for (Coordinate coordinate : MapController.getMapController().getMap().getPath()) {
            if (!isFirstTime) {
                path2.getElements().add(new LineTo(coordinate.getX(), coordinate.getY()));
            }
            isFirstTime = false;
        }

        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(path2);
        pathTransition.setDuration(Duration.seconds(raider.getSpeed()));
        pathTransition.setNode(raider);
        pathTransition.play();
//        pathTransition.setOnFinished(actionEvent -> {
//            if(!raider.isRaiderKilled()){
//                MapController.getMapController().setPassedRaiders(MapController.getMapController().getPassedRaiders()+1);
//                raiderCounter++;
//                System.out.println(raiderCounter);
//                if(MapController.getMapController().getPassedRaiders()==20){
//                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lost-page.fxml"));
//                    Scene scene = null;
//                    try {
//                        scene = new Scene(fxmlLoader.load(), 900, 400);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    stage.setTitle("Kingdom Rush");
//                    stage.setScene(scene);
//                    stage.show();
//                } else if (raiderCounter==30) {
//                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("win-page.fxml"));
//                    Scene scene = null;
//                    try {
//                        scene = new Scene(fxmlLoader.load(), 900, 400);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    stage.setTitle("Kingdom Rush");
//                    stage.setScene(scene);
//                    stage.show();
//                }
//            }
//        });
    }
    public void setTimelineForRaiders(ArrayList<ImageView> raiderImageViews,Raider raider,Wave wave,int i){
        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        for (int j = 1; j < raiderImageViews.size()+1; j++) {
            ImageView raiderImageView = raiderImageViews.get(j-1);
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(j * 200), (ActionEvent event3) -> {
                raider.setImage(raiderImageView.getImage());
            }
            ));
        }
        t.play();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e->{
            addRaiderToMap(i+1,wave);
        });
        pause.play();
    }
    public void addRaiderToMap(int i,Wave wave){
        if(i>=10){
            return;
        }
        Raider raider = wave.getRaiders().get(i);
        ArrayList<ImageView> raiderImageViews = new ArrayList<>();
        ArrayList<String> paths = raider.getPhotoAddresses();
        for (String path : paths) {
            Image image = new Image(HelloApplication.class.getResource(path).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(30);
            raiderImageViews.add(imageView);
        }
        raider.setFitWidth(20);
        raider.setFitHeight(30);

        pane.getChildren().add(raider);

        setPathForRaiders(raider);

        setTimelineForRaiders(raiderImageViews,raider,wave,i);

    }
    public void addNextButton(){
        if(nextWave_btn==null){
            nextWave_btn = new Button();
            nextWave_btn.setText("Start");
            nextWave_btn.setLayoutX(75);
            pane.getChildren().add(nextWave_btn);
            nextWave_btn.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white");
        }else{
            nextWave_btn.setText("Next Wave");
        }
    }
    @FXML
    void secondLevelAction(MouseEvent event) {
        addMapPic("second map.jpg");
        MapController.getMapController().setMap(SecondMap.getSecondMap());
        addNumberOfCoins();
        pane.getChildren().add(coinNumber);
        levelMap = new HashMap<>();
        towerImageViewMap = new HashMap<>();
        raiderCounter = 0;
        managePopUp();
        addNextButton();

        AtomicInteger waveIndex = new AtomicInteger(0);
        AtomicBoolean nextWave = new AtomicBoolean(false);
        addWavePic();
        updateWave(waveIndex);

        nextWave_btn.setOnMouseClicked(mouseEvent -> {
            nextWave.set(true);
            addNextButton();
        });

        new Thread(() -> {
            while (waveIndex.get() < 3) {
                Platform.runLater(() -> {
                    if (nextWave.get() && waveIndex.get() < 3) {
                        Wave wave = SecondMap.getSecondMap().getWaves().get(waveIndex.get());
                        addRaiderToMap(0,wave);
                        waveIndex.incrementAndGet();
                        updateWave(waveIndex);
                        nextWave.set(false);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    void thirdLevelAction(MouseEvent event) {
        addMapPic("third map.jpg");
        MapController.getMapController().setMap(ThirdMap.getThirdMap());
        addNumberOfCoins();
        pane.getChildren().add(coinNumber);
        levelMap = new HashMap<>();
        towerImageViewMap = new HashMap<>();
        raiderCounter = 0;
        managePopUp();
        addNextButton();

        AtomicInteger waveIndex = new AtomicInteger(0);
        AtomicBoolean nextWave = new AtomicBoolean(false);
        addWavePic();
        updateWave(waveIndex);

        nextWave_btn.setOnMouseClicked(mouseEvent -> {
            nextWave.set(true);
            addNextButton();
        });

        new Thread(() -> {
            while (waveIndex.get() < 3) {
                Platform.runLater(() -> {
                    if (nextWave.get() && waveIndex.get() < 3) {
                        Wave wave = ThirdMap.getThirdMap().getWaves().get(waveIndex.get());
                        addRaiderToMap(0,wave);
                        waveIndex.incrementAndGet();
                        updateWave(waveIndex);
                        nextWave.set(false);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
