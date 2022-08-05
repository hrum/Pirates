package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Biome {
    Ocean(R.drawable.ocean, 30),
    Land(R.drawable.land, 100);

    private int id;
    private int rarity;

    Biome(int id, int rarity) {
        this.id = id;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }
}
