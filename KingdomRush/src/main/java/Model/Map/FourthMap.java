package Model.Map;

import Model.Raiders.Crab;
import Model.Raiders.DarkNight;
import Model.Raiders.Ghost;
import Model.Raiders.Spider;

import java.util.ArrayList;

public class FourthMap extends Map{
    private static FourthMap fourthMap;
    private ArrayList<Coordinate> path2;

    private FourthMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins);

        ArrayList<Coordinate> path = getPath();
        path2 = new ArrayList<>();
        ArrayList<Coordinate> towerCoordinates = getTowerCoordinates();

        path.add(new Coordinate(372.8,372.0));
        path.add(new Coordinate(397.6,310.4));
        path.add(new Coordinate(521.6,311.2));
        path.add(new Coordinate(605.6,270.4));
        path.add(new Coordinate(635.2,192.0));
        path.add(new Coordinate(587.2,109.6));
        path.add(new Coordinate(484.8,37.6));
        path.add(new Coordinate(452.8,4.0));


        path2.add(new Coordinate(370.4,374.4));
        path2.add(new Coordinate(370.4,328.0));
        path2.add(new Coordinate(324.8,279.2));
        path2.add(new Coordinate(240.0,186.4));
        path2.add(new Coordinate(240.0,98.4));
        path2.add(new Coordinate(342.4,35.2));
        path2.add(new Coordinate(445.6,22.4));
        path2.add(new Coordinate(448.8,1.2));


        towerCoordinates.add(new Coordinate(506.4,348.8));
        towerCoordinates.add(new Coordinate(598.4,337.6));
        towerCoordinates.add(new Coordinate(392.0,269.6));
        towerCoordinates.add(new Coordinate(466.4,274.4));
        towerCoordinates.add(new Coordinate(524.8,268.0));
        towerCoordinates.add(new Coordinate(568.8,237.6));
        towerCoordinates.add(new Coordinate(563.2,182.4));
        towerCoordinates.add(new Coordinate(540.8,136.8));
        towerCoordinates.add(new Coordinate(213.6,236.8));
        towerCoordinates.add(new Coordinate(306.4,195.2));
        towerCoordinates.add(new Coordinate(288.0,160.8));
        towerCoordinates.add(new Coordinate(292.8,114.4));
        towerCoordinates.add(new Coordinate(324.8,83.2));
        towerCoordinates.add(new Coordinate(384.0,63.2));
        towerCoordinates.add(new Coordinate(450.4,72.8));

        getWaves().add(createWave(3,5,0,2));
        getWaves().add(createWave(0,6,4,0));
        getWaves().add(createWave(0,5,0,5));

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

    public static FourthMap getFourthMap() {
        if(fourthMap == null){
            fourthMap = new FourthMap(new Coordinate(452.8,4),900);
        }
        return fourthMap;
    }

    public ArrayList<Coordinate> getPath2() {
        return path2;
    }
}
