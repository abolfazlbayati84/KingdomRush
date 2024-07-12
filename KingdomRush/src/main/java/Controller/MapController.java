package Controller;

import Model.Raiders.DarkNight;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.kingdomrush.HelloApplication;
import org.example.kingdomrush.HomeController;
import Model.Map.Map;
import Model.Raiders.Ghost;
import Model.Raiders.Raider;
import Model.Tower.Archer;
import Model.Tower.MortarBomb;
import Model.Tower.Tower;
import Model.Tower.Vizard;

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
                    coins+=raider.getLoot();
                    HomeController.getCoinNumber().setText(" "+coins);
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
                    coins+=raider.getLoot();
                    HomeController.getCoinNumber().setText(" "+coins);
                });
            }

        });
    }
    public void damageNearRaiders(double x,double y,Pane pane){

        for(Node node : pane.getChildren()){
            if(node instanceof Raider && !(node instanceof Ghost)){
                Raider raider = (Raider) node;
                double tempDistance = Math.sqrt(Math.pow(y - node.getBoundsInParent().getCenterY()-5,2)+
                        Math.pow(x - node.getBoundsInParent().getCenterX()-5,2));

                if(tempDistance<100){
                    raider.setHealthCondition(raider.getHealthCondition()-20);

                }
                if(raider.getHealthCondition()<0){
                    Platform.runLater(()->{
                        pane.getChildren().remove(node);
                        raider.setRaiderKilled(true);
                        coins+=raider.getLoot();
                        HomeController.getCoinNumber().setText(" "+coins);
                    });
                }
            }
        }
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
            damageNearRaiders(node.getBoundsInParent().getCenterX(),node.getBoundsInParent().getCenterX(),pane);
            pane.getChildren().remove(stone);
            Raider raider = (Raider) node;
            if(raider.getHealthCondition()<0){
                Platform.runLater(()->{
                    pane.getChildren().remove(node);
                    raider.setRaiderKilled(true);
                    coins+=raider.getLoot();
                    HomeController.getCoinNumber().setText(" "+coins);
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
                Pane copyPane = pane;
                Platform.runLater(()->{
                for(Node node : copyPane.getChildren()){
                    if(node instanceof Raider){

                        Raider raider = (Raider) node;
                        double tempDistance = Math.sqrt(Math.pow(tower.getCoordinate().getY()- node.getBoundsInParent().getCenterY()-5,2)+
                                Math.pow(tower.getCoordinate().getX() - node.getBoundsInParent().getCenterX()-5,2));


                        if(tempDistance<tower.getAttackRange() && !(raider instanceof Ghost && tower instanceof MortarBomb) && towers.contains(tower)){
                            if(tower instanceof MortarBomb){
                                Platform.runLater(()->{
                                    sendStone(copyPane,raider,tower);
                                });
                            }
                            if(tower instanceof Archer){
                                Platform.runLater(()->{
                                    sendArrow(copyPane,raider,tower);
                                });
                            }
                            if(tower instanceof Vizard){
                                Platform.runLater(()->{
                                    sendFire(copyPane,raider,tower);
                                });
                            }
                            if(tower instanceof MortarBomb || !(raider.isHoldingShield()))
                            {
                                raider.setHealthCondition(raider.getHealthCondition()-tower.getDamagePower());
                            }else if(tower instanceof Vizard && raider.isHoldingShield()){
                                raider.setHoldingShield(false);
                            }else if(tower instanceof Archer && raider.isHoldingShield()){
                                raider.setHealthCondition(raider.getHealthCondition()-(tower.getDamagePower()/2));
                            }
                        }
                    }
                }
                });
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
