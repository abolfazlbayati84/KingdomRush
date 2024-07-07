package org.example.kingdomrush.Model.Map;

import org.example.kingdomrush.Model.Raiders.Crab;
import org.example.kingdomrush.Model.Raiders.DarkNight;
import org.example.kingdomrush.Model.Raiders.Ghost;
import org.example.kingdomrush.Model.Raiders.Spider;

import java.util.ArrayList;

public class ThirdMap extends Map{

    private static ThirdMap thirdMap;
    public ThirdMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins);

        ArrayList<Coordinate> towerCoordinates = getTowerCoordinates();
        ArrayList<Coordinate> path = getPath();

        towerCoordinates.add(new Coordinate(513.6,74.4));
        towerCoordinates.add(new Coordinate(449.6,70.4));
        towerCoordinates.add(new Coordinate(388.0,71.2));
        towerCoordinates.add(new Coordinate(293.6,142.4));
        towerCoordinates.add(new Coordinate(346.4,157.6));
        towerCoordinates.add(new Coordinate(407.2,160.0));
        towerCoordinates.add(new Coordinate(468.0,160.0));
        towerCoordinates.add(new Coordinate(577.6,217.6));
        towerCoordinates.add(new Coordinate(586.4,261.6));
        towerCoordinates.add(new Coordinate(522.4,252.0));
        towerCoordinates.add(new Coordinate(464.8,251.2));
        towerCoordinates.add(new Coordinate(401.6,252.0));
        towerCoordinates.add(new Coordinate(376.0,341.6));
        towerCoordinates.add(new Coordinate(438.4,338.4));
        towerCoordinates.add(new Coordinate(509.6,339.2));

        path.add(new Coordinate(443.2,3.2));
        path.add(new Coordinate(426.4,26.4));
        path.add(new Coordinate(335.2,40.8));
        path.add(new Coordinate(339.2,106.4));
        path.add(new Coordinate(490.4,117.6));
        path.add(new Coordinate(550.4,159.2));
        path.add(new Coordinate(498.4,207.2));
        path.add(new Coordinate(329.6,211.2));
        path.add(new Coordinate(355.2,280.0));
        path.add(new Coordinate(554.4,301.6));
        path.add(new Coordinate(567.2,361.6));
        path.add(new Coordinate(523.2,377.6));

        getWaves().add(createWave(3,0,2,5));
        getWaves().add(createWave(2,3,3,1));
        getWaves().add(createWave(0,5,2,3));
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

    public static ThirdMap getThirdMap() {
        if(thirdMap == null){
            thirdMap = new ThirdMap(new Coordinate(523.2,377.6),800);
        }
        return thirdMap;
    }
}
