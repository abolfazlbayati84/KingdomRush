package Model.Raiders;

public class DarkNight extends Raider{
    public DarkNight() {
        super(100, 50, 30);
        this.setFlying(false);
        this.setHoldingShield(true);
        getPhotoAddresses().add("darkknight1.png");
        getPhotoAddresses().add("darkknight2.png");
    }

    @Override
    public void move() {

    }
}
