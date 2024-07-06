package org.example.kingdomrush.Controller;

import org.example.kingdomrush.Model.Map.Map;
import org.example.kingdomrush.Model.Tower.Tower;

import java.util.ArrayList;

public class MapController {
    private static MapController mapController;
    private ArrayList<Tower> towers;

    public MapController() {
        towers = new ArrayList<>();
    }

    public static MapController getMapController() {
        if(mapController == null){
            mapController = new MapController();
        }
        return mapController;
    }
    public void addTower(Map map, Tower tower){
        map.setStartingCoins(map.getStartingCoins() - tower.getBuildPrice());
        towers.add(tower);
    }
}
