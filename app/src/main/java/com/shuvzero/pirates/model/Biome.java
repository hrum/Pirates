package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Biome {
    Ocean(R.drawable.ocean),
    Land(R.drawable.land);

    private int id;

    Biome(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
