package Model.Raiders;

public class Spider extends Raider{

    public Spider() {
        super(70, 20, 30);
        this.setFlying(false);
        getPhotoAddresses().add("spider1.png");
        getPhotoAddresses().add("spider2.png");
        getPhotoAddresses().add("spider3.png");
    }

    @Override
    public void move() {

    }
}
