package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(19, 9);
        MapGenerator mapGenerator = new MapGenerator(treasureMap);
        mapGenerator.generate();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }
}
