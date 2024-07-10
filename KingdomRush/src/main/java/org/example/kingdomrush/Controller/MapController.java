package org.example.kingdomrush.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.kingdomrush.HelloApplication;
import org.example.kingdomrush.HomeController;
import org.example.kingdomrush.Model.Map.Map;
import org.example.kingdomrush.Model.Raiders.Ghost;
import org.example.kingdomrush.Model.Raiders.Raider;
import org.example.kingdomrush.Model.Tower.Archer;
import org.example.kingdomrush.Model.Tower.MortarBomb;
import org.example.kingdomrush.Model.Tower.Tower;
import org.example.kingdomrush.Model.Tower.Vizard;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MapController {
    private static MapController mapController;
    private ArrayList<Tower> towers;
    private int coins;
    private Map map;
    private int passedRaiders;
    public MapController() {
        towers = new ArrayList<>();
    }

    public static MapController getMapController() {
        if(mapController == null){
            mapController = new MapController();
        }
        return mapController;
    }
    public void destroyTower(Tower tower){
        towers.remove(tower);
        tower.getTimer().cancel();
        coins+=(tower.getBuildPrice())/2;
        HomeController.getCoinNumber().setText(" "+coins);
    }
    public void sendFire(Pane pane,Node node,Tower tower){
        String path = HelloApplication.class.getResource("fire.png").toExternalForm();
        Image image = new Image(path);
        ImageView fire = new ImageView(image);
        fire.setFitWidth(10);
        fire.setFitHeight(10);
        fire.setLayoutX(tower.getCoordinate().getX());
        fire.setLayoutY(tower.getCoordinate().getY());
        Platform.runLater(()->{
            pane.getChildren().addAll(fire);
        });
        Timeline timeline = new Timeline();
        float initialX = (float) (tower.getCoordinate().getX());
        float initialY = (float) (tower.getCoordinate().getY());

        double shiftX = node.getBoundsInParent().getCenterX() - initialX;
        double shiftY = node.getBoundsInParent().getCenterY() - initialY;

        KeyValue keyValueX = new KeyValue(fire.translateXProperty(), shiftX);
        KeyValue keyValueY = new KeyValue(fire.translateYProperty(), shiftY);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),  keyValueY,keyValueX);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(e->{
            pane.getChildren().remove(fire);
            Raider raider = (Raider) node;
            if(raider.getHealthCondition()<0){
                Platform.runLater(()->{
                    pane.getChildren().remove(node);
                    raider.setRaiderKilled(true);
                    //r.getTimeLineAnimation().stop();
                });
            }

        });
    }
    public void sendArrow(Pane pane,Node node,Tower tower){
        String path = HelloApplication.class.getResource("arrow.png").toExternalForm();
        Image image = new Image(path);
        ImageView arrow = new ImageView(image);
        arrow.setFitWidth(10);
        arrow.setFitHeight(10);
        arrow.setLayoutX(tower.getCoordinate().getX());
        arrow.setLayoutY(tower.getCoordinate().getY());
        Platform.runLater(()->{
            pane.getChildren().addAll(arrow);
        });
        Timeline timeline = new Timeline();
        float initialX = (float) (tower.getCoordinate().getX());
        float initialY = (float) (tower.getCoordinate().getY());

        double shiftX = node.getBoundsInParent().getCenterX() - initialX;
        double shiftY = node.getBoundsInParent().getCenterY() - initialY;

        KeyValue keyValueX = new KeyValue(arrow.translateXProperty(), shiftX);
        KeyValue keyValueY = new KeyValue(arrow.translateYProperty(), shiftY);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),  keyValueY,keyValueX);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(e->{
            pane.getChildren().remove(arrow);
            Raider raider = (Raider) node;
            if(raider.getHealthCondition()<0){
                Platform.runLater(()->{
                    pane.getChildren().remove(node);
                    raider.setRaiderKilled(true);
                    //r.getTimeLineAnimation().stop();
                });
            }

        });
    }

    public void sendStone(Pane pane, Node node,Tower tower){

        String path = HelloApplication.class.getResource("stone.png").toExternalForm();
        Image image = new Image(path);
        ImageView stone = new ImageView(image);
        stone.setFitWidth(30);
        stone.setFitHeight(30);
        stone.setLayoutX(tower.getCoordinate().getX());
        stone.setLayoutY(tower.getCoordinate().getY());
        Platform.runLater(()->{
            pane.getChildren().addAll(stone);
        });
        Timeline timeline = new Timeline();
        float initialX = (float) (tower.getCoordinate().getX());
        float initialY = (float) (tower.getCoordinate().getY());

        double shiftX = node.getBoundsInParent().getCenterX() - initialX;
        double shiftY = node.getBoundsInParent().getCenterY() - initialY;

        KeyValue keyValueX = new KeyValue(stone.translateXProperty(), shiftX);
        KeyValue keyValueY = new KeyValue(stone.translateYProperty(), shiftY);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),  keyValueY,keyValueX);
        timeline.getKeyFrames().add(keyFrame);

        timeline.setCycleCount(1);
        timeline.play();
        timeline.setOnFinished(e->{
            pane.getChildren().remove(stone);
            Raider raider = (Raider) node;
            if(raider.getHealthCondition()<0){
                Platform.runLater(()->{
                    pane.getChildren().remove(node);
                    raider.setRaiderKilled(true);
                    //r.getTimeLineAnimation().stop();
                });
            }

        });
    }
    public void towerWakeUp(Pane pane,Tower tower){
        Timer timer = new Timer();
        tower.setTimer(timer);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(Node node : pane.getChildren()){
                    if(node instanceof Raider){

                        Raider raider = (Raider) node;
                        double tempDistance = Math.sqrt(Math.pow(tower.getCoordinate().getY()- node.getBoundsInParent().getCenterY()-5,2)+
                                Math.pow(tower.getCoordinate().getX() - node.getBoundsInParent().getCenterX()-5,2));


                        if(tempDistance<tower.getAttackRange() && !(raider instanceof Ghost && tower instanceof MortarBomb) && towers.contains(tower)){
                            if(tower instanceof MortarBomb){
                                Platform.runLater(()->{
                                    sendStone(pane,raider,tower);
                                });
                            }
                            if(tower instanceof Archer){
                                Platform.runLater(()->{
                                    sendArrow(pane,raider,tower);
                                });
                            }
                            if(tower instanceof Vizard){
                                Platform.runLater(()->{
                                    sendFire(pane,raider,tower);
                                });
                            }
                            raider.setHealthCondition(raider.getHealthCondition()-tower.getDamagePower());
                        }
                        if(raider.getHealthCondition()<0){

                            Platform.runLater(()->{
                                pane.getChildren().remove(raider);
                                coins+=raider.getLoot();
                                raider.setRaiderKilled(true);
                                HomeController.getCoinNumber().setText(" "+coins);
                            });
                            break;
                        }
                    }
                }
            }
        },1000,500);

    }
    public boolean upgradeTower(Tower tower){
        if(coins>=110 && (tower.getLevel() < PlayerController.getPlayerController().getPlayer().getLevel())){
            coins-=110;
            tower.setLevel(tower.getLevel()+1);
            tower.setDamagePower(tower.getDamagePower()+5);
            return true;
        }else{
            return false;
        }
    }
    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        coins = map.getStartingCoins();
    }

    public int getPassedRaiders() {
        return passedRaiders;
    }

    public void setPassedRaiders(int passedRaiders) {
        this.passedRaiders = passedRaiders;
    }

    public void addTower(Tower tower, Pane pane){
        coins = coins - tower.getBuildPrice();
        towerWakeUp(pane,tower);
        towers.add(tower);
    }
}
