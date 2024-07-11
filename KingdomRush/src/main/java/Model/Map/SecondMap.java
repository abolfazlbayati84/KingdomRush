package Model.Map;

import Model.Raiders.Crab;
import Model.Raiders.DarkNight;
import Model.Raiders.Ghost;
import Model.Raiders.Spider;

import java.util.ArrayList;

public class SecondMap extends Map{
    private static SecondMap secondMap;
    private SecondMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins);

        ArrayList<Coordinate> towerCoordinates = getTowerCoordinates();
        ArrayList<Coordinate> path = getPath();

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

        getWaves().add(createWave(5,0,0,5));
        getWaves().add(createWave(4,3,3,0));
        getWaves().add(createWave(3,3,2,2));

    }
    public Wave createWave(int crabs,int darkNights,int ghosts,int spiders){
        Wave wave = new Wave();
        for(int i=0 ; i<crabs ; i++){
            wave.getRaiders().add(new Crab());
        }
        for(int i=0 ; i<darkNights ; i++){
            wave.getRaiders().add(new DarkNight());
        }
        for(int i=0 ; i<ghosts ; i++){
            wave.getRaiders().add(new Ghost());
        }
        for(int i=0 ; i<spiders ; i++){
            wave.getRaiders().add(new Spider());
        }
        return wave;
    }

    public static SecondMap getSecondMap() {
        if(secondMap == null){
            secondMap = new SecondMap(new Coordinate(444.8,348.0),500);
        }
        return secondMap;
    }
}
