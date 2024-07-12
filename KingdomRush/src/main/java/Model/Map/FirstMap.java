package Model.Map;

import Model.Raiders.*;

import java.util.ArrayList;

public class FirstMap extends Map{
    private static FirstMap firstMap;
    private FirstMap(Coordinate endPoint, int startingCoins) {
        super(endPoint, startingCoins,1);

        this.getTowerCoordinates().add(new Coordinate(439.2,324));
        this.getTowerCoordinates().add(new Coordinate(330.4,112));
        this.getTowerCoordinates().add(new Coordinate(278.4,129.6));
        this.getTowerCoordinates().add(new Coordinate(332,202.4));
        this.getTowerCoordinates().add(new Coordinate(332.8,250.4));
        this.getTowerCoordinates().add(new Coordinate(393.6,250.4));
        this.getTowerCoordinates().add(new Coordinate(503.2,260));
        this.getTowerCoordinates().add(new Coordinate(596,303.6));

        getPath().add(new Coordinate(395.2,4));
        getPath().add(new Coordinate(401.6,97.6));
        getPath().add(new Coordinate(373.6,136.0));
        getPath().add(new Coordinate(309.6,167.2));
        getPath().add(new Coordinate(275.2,208.8));
        getPath().add(new Coordinate(276.8,260.0));
        getPath().add(new Coordinate(320.8,290.4));
        getPath().add(new Coordinate(402.4,293.6));
        getPath().add(new Coordinate(462.4,284.0));
        getPath().add(new Coordinate(517.6,301.6));
        getPath().add(new Coordinate(586.4,242.4));
        getPath().add(new Coordinate(808.0,225.6));

        getWaves().add(createWave(10,0,0,0));
        getWaves().add(createWave(8,2,0,0));
        getWaves().add(createWave(5,3,2,0));

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

    public static FirstMap getFirstMap() {
        if(firstMap == null){
            firstMap = new FirstMap(new Coordinate(808.0,225.6),400);
        }
        return firstMap;
    }
}
