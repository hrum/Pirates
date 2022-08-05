package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum CellObject {

    Anchor(R.drawable.anchor),
    Cactus(R.drawable.cactus),
    Cave(R.drawable.cave),
    Chest(R.drawable.chest),
    House(R.drawable.house),
    Lake(R.drawable.lake),
    Mark(R.drawable.mark),
    Mountain(R.drawable.mountain),
    Palm(R.drawable.palm),
    River(R.drawable.river),
    Skull(R.drawable.skull),
    Volcano(R.drawable.volcano);

    private int id;

    CellObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
