package org.example.kingdomrush.Controller;

import org.example.kingdomrush.Model.Map.Map;
import org.example.kingdomrush.Model.Tower.Tower;

import java.util.ArrayList;

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

    public void addTower(Tower tower){
        coins = coins - tower.getBuildPrice();
        towers.add(tower);
    }
}
