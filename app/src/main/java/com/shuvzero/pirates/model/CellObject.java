package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum CellObject {

    Anchor(R.drawable.anchor, 100, true),
    Cactus(R.drawable.cactus, 100, true),
    Cave(R.drawable.cave, 100, true),
    Chest(R.drawable.chest, 100, true),
    House(R.drawable.house, 100, true),
    Lake(R.drawable.lake, 100, true),
    Mark(R.drawable.mark, 100, true),
    Mountain(R.drawable.mountain, 100, true),
    Palm(R.drawable.palm, 100, true),
    River(R.drawable.river,100, true),
    Skull(R.drawable.skull,100, true),
    Volcano(R.drawable.volcano, 100, true);

    private int id;
    private int rarity;
    private boolean isLand;

    CellObject(int id, int rarity, boolean isLand) {
        this.id = id;
        this.rarity = rarity;
        this.isLand = isLand;
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }

    public boolean isLand() {
        return isLand;
    }
}
