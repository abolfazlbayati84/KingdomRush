package Model.Tower;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import Model.Map.Coordinate;

import java.util.Timer;

abstract public class Tower extends ImageView{
    private int damagePower;
    private int buildPrice;
    private double x;
    private double y;
    private Coordinate coordinate;
    private int attackRange;
    private int level;
    private Timer timer;

    public Tower(int damagePower, int buildPrice, Coordinate coordinate,int attackRange) {
        this.damagePower = damagePower;
        this.buildPrice = buildPrice;
        this.coordinate = coordinate;
        this.attackRange = attackRange;
        this.level = 1;
    }
    abstract void defence();

    abstract void promoteTower();

    abstract void ruin();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getDamagePower() {
        return damagePower;
    }

    public int getBuildPrice() {
        return buildPrice;
    }

    public void setDamagePower(int damagePower) {
        this.damagePower = damagePower;
    }

    public void setBuildPrice(int buildPrice) {
        this.buildPrice = buildPrice;
    }

}