package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Feature {

    River(FeatureType.Linear, R.drawable.river, R.string.river, true),
    Road(FeatureType.Linear, R.drawable.road, R.string.road, true),

    Grass(FeatureType.Flat, R.drawable.grass, R.string.grass, true),
    Forest(FeatureType.Flat, R.drawable.forest, R.string.forest, true),
    Hills(FeatureType.Flat, R.drawable.hills, R.string.hills, true),
    Swamp(FeatureType.Flat, R.drawable.swamp, R.string.swamp, true),
    Plains(FeatureType.Flat, R.drawable.plains, R.string.plains, true),
    Jungle(FeatureType.Flat, R.drawable.jungle, R.string.jungle, true),

    Mountain(FeatureType.Single, R.drawable.mountain, R.string.mountain, true),
    Cave(FeatureType.Single, R.drawable.cave, R.string.cave, true),
    Skull(FeatureType.Single, R.drawable.skull, R.string.skull, true),
    Volcano(FeatureType.Single, R.drawable.volcano, R.string.volcano, true),
    Palm(FeatureType.Single, R.drawable.palm, R.string.palm, true),
    House(FeatureType.Single, R.drawable.house, R.string.house, true),
    Cactus(FeatureType.Single, R.drawable.cactus, R.string.cactus, true),
    Cross(FeatureType.Single, R.drawable.cross, R.string.cross, true),
    Chest(FeatureType.Single, R.drawable.chest, R.string.chest, true),
    Desert(FeatureType.Single, 0, R.string.desert, true),
    Lake(FeatureType.Single, R.drawable.lake, R.string.lake, true),

    Sea(FeatureType.Flat, R.drawable.sea, R.string.sea, false),

    Anchor(FeatureType.Single, R.drawable.anchor, R.string.anchor, false),
    Shark(FeatureType.Single, R.drawable.shark, R.string.shark, false),
    Coral(FeatureType.Single, R.drawable.coral, R.string.coral, false),
    Turtle(FeatureType.Single, R.drawable.turtle, R.string.turtle, false),
    Jellyfish(FeatureType.Single, R.drawable.jellyfish, R.string.jellyfish, false),
    Octopus(FeatureType.Single, R.drawable.octopus, R.string.octopus, false),
    Ocean(FeatureType.Single, 0, R.string.ocean, false);


    private FeatureType featureType;
    private int drawableId;
    private int stringId;
    private boolean isLand;

    Feature(FeatureType featureType, int drawableId, int stringId, boolean isLand) {
        this.featureType = featureType;
        this.drawableId = drawableId;
        this.stringId = stringId;
        this.isLand = isLand;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getStringId() {
        return stringId;
    }

    public boolean isLand() {
        return isLand;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }
}
