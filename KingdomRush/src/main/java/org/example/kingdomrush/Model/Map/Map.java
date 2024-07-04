package org.example.kingdomrush.Model.Map;

import java.util.ArrayList;

public class Map {
    private ArrayList<Coordinate> towerCoordinates;
    private ArrayList<Coordinate> path;
    private Coordinate endPoint;
    private int startingCoins;

    public Map(Coordinate endPoint, int startingCoins) {
//        this.towerCoordinates = towerCoordinates;
//        this.path = path;
        this.endPoint = endPoint;
        this.startingCoins = startingCoins;
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

    public void setTowerCoordinates(ArrayList<Coordinate> towerCoordinates) {
        this.towerCoordinates = towerCoordinates;
    }

    public void setPath(ArrayList<Coordinate> path) {
        this.path = path;
    }

    public void setEndPoint(Coordinate endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartingCoins(int startingCoins) {
        this.startingCoins = startingCoins;
    }
}
