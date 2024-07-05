package org.example.kingdomrush.Model.Map;

import java.util.ArrayList;

public class SecondMap extends Map{
    private static SecondMap secondMap;
    private SecondMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins);

        ArrayList<Coordinate> towerCoordinates = new ArrayList<>();
        ArrayList<Coordinate> path = new ArrayList<>();

        towerCoordinates.add(new Coordinate(481.6,80.0));
        towerCoordinates.add(new Coordinate(481.6,130.4));
        towerCoordinates.add(new Coordinate(373.6,92.0));
        towerCoordinates.add(new Coordinate(332.8,177.6));
        towerCoordinates.add(new Coordinate(436.8,258.4));
        towerCoordinates.add(new Coordinate(377.6,269.6));
        towerCoordinates.add(new Coordinate(444.8,348.0));

        path.add(new Coordinate(820.0,101.6));
        path.add(new Coordinate(778.4,102.4));
        path.add(new Coordinate(708.0,129.6));
        path.add(new Coordinate(547.2,127.2));
        path.add(new Coordinate(508.8,46.4));
        path.add(new Coordinate(443.2,48.0));
        path.add(new Coordinate(425.6,109.6));
        path.add(new Coordinate(384.0,135.2));
        path.add(new Coordinate(300.0,145.6));
        path.add(new Coordinate(275.2,201.6));
        path.add(new Coordinate(331.2,228.8));
        path.add(new Coordinate(434.4,212.8));
        path.add(new Coordinate(488.8,231.2));
        path.add(new Coordinate(492.8,292.0));
        path.add(new Coordinate(423.2,318.4));
        path.add(new Coordinate(380.0,358.4));
        path.add(new Coordinate(380.8,375.2));

        this.setTowerCoordinates(towerCoordinates);
        this.setPath(path);
    }

    public static SecondMap getSecondMap() {
        if(secondMap == null){
            secondMap = new SecondMap(new Coordinate(444.8,348.0),500);
        }
        return secondMap;
    }
}
