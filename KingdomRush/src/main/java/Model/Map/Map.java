package Model.Map;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Coordinate> towerCoordinates;
    private final ArrayList<Coordinate> path;
    private final Coordinate endPoint;
    private final int startingCoins;
    private final ArrayList<Wave> waves;
    private final int level;

    public Map(Coordinate endPoint, int startingCoins, int level) {
        this.endPoint = endPoint;
        this.startingCoins = startingCoins;
        this.level = level;
        this.waves = new ArrayList<>();
        towerCoordinates = new ArrayList<>();
        path = new ArrayList<>();
    }

    public ArrayList<Coordinate> getTowerCoordinates() {
        return towerCoordinates;
    }

    public ArrayList<Coordinate> getPath() {
        return path;
    }

    public Coordinate getEndPoint() {
        return endPoint;
    }

    public int getStartingCoins() {
        return startingCoins;
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public int getLevel() {
        return level;
    }
}
