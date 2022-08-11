package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Feature {


    River(FeatureType.Linear, R.drawable.river, true),
    Road(FeatureType.Linear, R.drawable.road, true),

    Grass(FeatureType.Flat, -1, true),
    Forest(FeatureType.Flat, -1, true),
    Hill(FeatureType.Flat, -1, true),
    Swamp(FeatureType.Flat, -1, true),

    Mountain(FeatureType.Single, R.drawable.mountain, true),
    Cave(FeatureType.Single, R.drawable.cave, true),
    Skull(FeatureType.Single, R.drawable.skull, true),
    Volcano(FeatureType.Single, R.drawable.volcano, true),
    Palm(FeatureType.Single, R.drawable.palm, true),
    House(FeatureType.Single, R.drawable.house, true),
    Cactus(FeatureType.Single, R.drawable.cactus, true),
    Cross(FeatureType.Single, R.drawable.cross, true),
    Chest(FeatureType.Single, R.drawable.chest, true),

    Sea(FeatureType.Flat, -1, false),

    Anchor(FeatureType.Single, R.drawable.anchor, false),
    Lake(FeatureType.Single, R.drawable.lake, false),
    Shark(FeatureType.Single, R.drawable.shark, false),
    Coral(FeatureType.Single, R.drawable.coral, false),
    Turtle(FeatureType.Single, -1, false),
    Jellyfish(FeatureType.Single, -1, false),
    Octopus(FeatureType.Single, -1, false);



    private FeatureType featureType;
    private int id;
    private boolean isLand;

    Feature(FeatureType featureType, int id, boolean isLand) {
        this.featureType = featureType;
        this.id = id;
        this.isLand = isLand;
    }

    public int getId() {
        return id;
    }

    public boolean isLand() {
        return isLand;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }
}
