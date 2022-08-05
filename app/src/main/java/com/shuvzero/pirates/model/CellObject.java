package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum CellObject {

    Empty(R.drawable.empty, 100),
    Anchor(R.drawable.anchor, 100),
    Cactus(R.drawable.cactus, 100),
    Cave(R.drawable.cave, 100),
    Chest(R.drawable.chest, 100),
    House(R.drawable.house, 100),
    Lake(R.drawable.lake, 100),
    Mark(R.drawable.mark, 100),
    Mountain(R.drawable.mountain, 100),
    Palm(R.drawable.palm, 100),
    River(R.drawable.river,100),
    Skull(R.drawable.skull,100),
    Volcano(R.drawable.volcano, 100);

    private int id;
    private int rarity;

    CellObject(int id, int rarity) {
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
