package org.example.kingdomrush.Model.Player;

public enum Spells implements spell{
    HealthKit(100),Freeze(150),Coin(50),LittleChild(250);
    final int price;

    private Spells(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void drop(Spells spellType) {

        if(spellType.equals(HealthKit)){
            //TODO
        }
        if(spellType.equals(Freeze)){
            //TODO
        }
        if(spellType.equals(Coin)){
            //TODO
        }
        if(spellType.equals(LittleChild)){
            //TODO
        }
    }
}
