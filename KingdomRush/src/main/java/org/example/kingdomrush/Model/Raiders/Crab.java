package org.example.kingdomrush.Model.Raiders;

public class Crab extends Raider{

    public Crab() {
        super(70, 50, 20);
        this.setFlying(false);
        getPhotoAddresses().add("crab1.png");
        getPhotoAddresses().add("crab2.png");
    }

    @Override
    public void move() {

    }
}
