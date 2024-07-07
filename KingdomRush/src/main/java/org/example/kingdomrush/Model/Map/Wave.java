package org.example.kingdomrush.Model.Map;

import org.example.kingdomrush.Model.Raiders.Raider;

import java.util.ArrayList;

public class Wave {
    private ArrayList<Raider> raiders;

    public Wave() {
        this.raiders = new ArrayList<>();
    }

    public void setRaiders(ArrayList<Raider> raiders) {
        this.raiders = raiders;
    }

    public ArrayList<Raider> getRaiders() {
        return raiders;
    }
}
