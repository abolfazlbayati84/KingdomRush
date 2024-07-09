package org.example.kingdomrush.Model.Tower;

import javafx.scene.image.ImageView;
import org.example.kingdomrush.Model.Map.Coordinate;

abstract public class Tower extends ImageView{
    private int damagePower;
    private int buildPrice;
    private double x;
    private double y;
    private Coordinate coordinate;

    public Tower(int damagePower, int buildPrice, Coordinate coordinate) {
        this.damagePower = damagePower;
        this.buildPrice = buildPrice;
        this.coordinate = coordinate;
    }
    abstract void defence();
    abstract void promoteTower();
    abstract void ruin();

    public int getDamagePower() {
        return damagePower;
    }

    public int getBuildPrice() {
        return buildPrice;
    }

//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }

    public void setDamagePower(int damagePower) {
        this.damagePower = damagePower;
    }

    public void setBuildPrice(int buildPrice) {
        this.buildPrice = buildPrice;
    }

//    public void setX(double x) {
//        this.x = x;
//    }
//
//    public void setY(double y) {
//        this.y = y;
//    }
}
