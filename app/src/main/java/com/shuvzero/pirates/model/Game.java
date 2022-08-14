package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(24, 9);
        MapGenerator mapGenerator = new MapGenerator(treasureMap);
        mapGenerator.generate();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }

    public int dig(int position) {
        int hint = 0;
        for(Feature feature: treasureMap.getFeatures(position))
            if(treasureMap.getTreasureFeatures().contains(feature))
                hint++;
        return hint;
    }
}
