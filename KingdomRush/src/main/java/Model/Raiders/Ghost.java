package Model.Raiders;

public class Ghost extends Raider{
    public Ghost() {
        super(70, 40, 30);
        this.setFlying(true);
        getPhotoAddresses().add("Ghost1.png");
        getPhotoAddresses().add("Ghost2.png");
        getPhotoAddresses().add("Ghost3.png");
    }
    @Override
    public void move() {

    }
}
