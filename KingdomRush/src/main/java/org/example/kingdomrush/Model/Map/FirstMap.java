package org.example.kingdomrush.Model.Map;

import java.util.ArrayList;

public class FirstMap extends Map{
    private static FirstMap firstMap;
    private FirstMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins);
        ArrayList<Coordinate> towerCoordinates = new ArrayList<>();
        ArrayList<Coordinate> path = new ArrayList<>();

        towerCoordinates.add(new Coordinate(439.2,324));
        towerCoordinates.add(new Coordinate(330.4,112));
        towerCoordinates.add(new Coordinate(278.4,129.6));
        towerCoordinates.add(new Coordinate(332,202.4));
        towerCoordinates.add(new Coordinate(332.8,250.4));
        towerCoordinates.add(new Coordinate(393.6,250.4));
        towerCoordinates.add(new Coordinate(503.2,260));
        towerCoordinates.add(new Coordinate(596,303.6));

        path.add(new Coordinate(395.2,4));
        path.add(new Coordinate(401.6,97.6));
        path.add(new Coordinate(373.6,136.0));
        path.add(new Coordinate(309.6,167.2));
        path.add(new Coordinate(275.2,208.8));
        path.add(new Coordinate(276.8,260.0));
        path.add(new Coordinate(320.8,290.4));
        path.add(new Coordinate(402.4,293.6));
        path.add(new Coordinate(462.4,284.0));
        path.add(new Coordinate(517.6,301.6));
        path.add(new Coordinate(586.4,242.4));
        path.add(new Coordinate(808.0,225.6));
//        path.add(new Coordinate(600.8,276));
//        path.add(new Coordinate(655.2,254.4));
//        path.add(new Coordinate(770.4,248.8));

        this.setPath(path);
        this.setTowerCoordinates(towerCoordinates);
    }

    public static FirstMap getFirstMap() {
        if(firstMap == null){
            firstMap = new FirstMap(new Coordinate(808.0,225.6),400);
        }
        return firstMap;
    }
}
