package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Feature {

    Anchor(R.drawable.anchor, true),
    Cactus(R.drawable.cactus, true),
    Cave(R.drawable.cave, true),
    Chest(R.drawable.chest, true),
    Cross(R.drawable.cross, true),
    House(R.drawable.house, true),
    Lake(R.drawable.lake, true),
    Mark(R.drawable.mark, true),
    Mountain(R.drawable.mountain, true),
    Palm(R.drawable.palm, true),
    River(R.drawable.river, true),
    Road(R.drawable.road, true),
    Skull(R.drawable.skull, true),
    Volcano(R.drawable.volcano, true),

    Shark(R.drawable.shark, false),
    Coral(R.drawable.coral, false);



    private int id;
    private boolean isLand;

    Feature(int id, boolean isLand) {
        this.id = id;
        this.isLand = isLand;
    }

    public int getId() {
        return id;
    }

    public boolean isLand() {
        return isLand;
    }
}
