package org.example.kingdomrush.Model.Map;

import java.util.ArrayList;

public class FirstMap extends Map{
    private static FirstMap firstMap;
    private FirstMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins);
        ArrayList<Coordinate> towerCoordinates = new ArrayList<>();
        ArrayList<Coordinate> path = new ArrayList<>();

        towerCoordinates.add(new Coordinate(374.2,118.4));
        towerCoordinates.add(new Coordinate(292.8,136));
        towerCoordinates.add(new Coordinate(352.8,215.2));
        towerCoordinates.add(new Coordinate(349.6,246.8));
        towerCoordinates.add(new Coordinate(414.4,267.2));
        towerCoordinates.add(new Coordinate(461.6,348.8));
        towerCoordinates.add(new Coordinate(531.2,275.2));
        towerCoordinates.add(new Coordinate(626.4,324.8));

        path.add(new Coordinate(413.6,4));
        path.add(new Coordinate(416.8,120.8));
        path.add(new Coordinate(397.6,142.4));
        path.add(new Coordinate(324,176.8));
        path.add(new Coordinate(291.2,218.4));
        path.add(new Coordinate(289.6,262.4));
        path.add(new Coordinate(322.4,299.2));
        path.add(new Coordinate(378.4,316));
        path.add(new Coordinate(437.6,305.6));
        path.add(new Coordinate(476,303.2));
        path.add(new Coordinate(516,317.6));
        path.add(new Coordinate(570.4,303.2));
        path.add(new Coordinate(600.8,276));
        path.add(new Coordinate(655.2,254.4));
        path.add(new Coordinate(770.4,248.8));

        this.setPath(path);
        this.setTowerCoordinates(towerCoordinates);
    }

    public static FirstMap getFirstMap() {
        if(firstMap == null){
            firstMap = new FirstMap(new Coordinate(770.4,248.8),400);
        }
        return firstMap;
    }
}
