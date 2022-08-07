package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(25, 10);
        treasureMap.generate();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }
}
