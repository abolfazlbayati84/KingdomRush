package org.example.kingdomrush.Model.Raiders;

public class Ghost extends Raider{
    public Ghost() {
        super(70, 50, 30);
        this.setFlying(true);
        getPhotoAddresses().add("Ghost1.png");
        getPhotoAddresses().add("Ghost2.png");
        getPhotoAddresses().add("Ghost3.png");
    }
    @Override
    public void move() {

    }
}
