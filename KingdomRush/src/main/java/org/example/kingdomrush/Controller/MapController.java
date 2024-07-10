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
import org.example.kingdomrush.Model.Tower.MortarBomb;
import org.example.kingdomrush.Model.Tower.Tower;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MapController {
    private static MapController mapController;
    private ArrayList<Tower> towers;
    private int coins;
    private Map map;
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
        coins+=(tower.getBuildPrice())/2;
        HomeController.getCoinNumber().setText(" "+coins);
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
                    //r.getTimeLineAnimation().stop();
                });
            }

        });
    }
    public void towerWakeUp(Pane pane,Tower tower){
        Timer timer = new Timer();
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
                            raider.setHealthCondition(raider.getHealthCondition()-tower.getDamagePower());
                        }
                        if(raider.getHealthCondition()<0){

                            Platform.runLater(()->{
                                pane.getChildren().remove(raider);
                                coins+=raider.getLoot();
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

    public void addTower(Tower tower,Pane pane){
        coins = coins - tower.getBuildPrice();
        towerWakeUp(pane,tower);
        towers.add(tower);
    }
}
