package org.example.kingdomrush.Controller;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.kingdomrush.HomeController;
import org.example.kingdomrush.Model.Map.Map;
import org.example.kingdomrush.Model.Raiders.Raider;
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

    public void towerWakeUp(Pane pane,Tower tower){
        Timer timer = new Timer();
        final int[] i = {0};
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(Node node : pane.getChildren()){
                    if(node instanceof Raider){
                        System.out.println("A");
                        Raider raider = (Raider) node;
                        double tempDistance = Math.sqrt(Math.pow(tower.getCoordinate().getY()- node.getBoundsInParent().getCenterY()-5,2)+
                                Math.pow(tower.getCoordinate().getX() - node.getBoundsInParent().getCenterX()-5,2));

                        System.out.println(tempDistance);
                        //System.out.println("Tower X = "+tower.getBoundsInParent().getCenterX()+"\t Y = "+tower.getBoundsInParent().getCenterY());
                        if(tempDistance<tower.getAttackRange()){
                            //TODO
                            System.out.println("B");
                            System.out.println(i[0]++);
                            raider.setHealthCondition(raider.getHealthCondition()-tower.getDamagePower());
                        }
                        if(raider.getHealthCondition()<0){
                            System.out.println("C");
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

//        new Thread (()->{
//            while(true){
//                for(Node node : pane.getChildren()){
//                    System.out.println("A");
//                    if(node instanceof Raider){
//                        System.out.println("B");
//                        Raider raider = (Raider) node;
//                        double tempDistance = Math.sqrt(Math.pow(tower.getBoundsInParent().getCenterY() - node.getBoundsInParent().getCenterY(),2)+
//                                Math.pow(tower.getBoundsInParent().getCenterX() - node.getBoundsInParent().getCenterX(),2));
//
//                        if(tempDistance<tower.getAttackRange()){
//                            //TODO
//                            System.out.println("C");
//                            System.out.println(i[0]++);
//                            raider.setHealthCondition(raider.getHealthCondition()-tower.getDamagePower());
//                        }
//                        if(raider.getHealthCondition()<0){
//                            System.out.println("D");
//                            Platform.runLater(()->{
//                                pane.getChildren().remove(raider);
//                                coins+=raider.getLoot();
//                                HomeController.getCoinNumber().setText(" "+coins);
//                            });
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
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
