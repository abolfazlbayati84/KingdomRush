package Model.Raiders;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

abstract public class Raider extends ImageView {
    private int healthCondition;
    private int speed;
    private int loot;
    private boolean isFlying;
    private boolean holdingShield;
    private ArrayList<String> photoAddresses;
    private boolean isRaiderKilled;
    private PathTransition pathTransition;

    public Raider(int healthCondition, int speed, int loot) {
        this.healthCondition = healthCondition;
        this.speed = speed;
        this.loot = loot;
        photoAddresses = new ArrayList<>();
        isRaiderKilled = false;
    }

    public boolean isHoldingShield() {
        return holdingShield;
    }

    public void setHoldingShield(boolean holdingShield) {
        this.holdingShield = holdingShield;
    }

    abstract public void move();

    public int getHealthCondition() {
        return healthCondition;
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLoot() {
        return loot;
    }

    public ArrayList<String> getPhotoAddresses() {
        return photoAddresses;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public boolean isRaiderKilled() {
        return isRaiderKilled;
    }

    public void setRaiderKilled(boolean raiderKilled) {
        isRaiderKilled = raiderKilled;
    }

    public void setHealthCondition(int healthCondition) {
        this.healthCondition = healthCondition;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    public void setPathTransition(PathTransition pathTransition) {
        this.pathTransition = pathTransition;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    public void setPhotoAddresses(ArrayList<String> photoAddresses) {
        this.photoAddresses = photoAddresses;
    }
}
