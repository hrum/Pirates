package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(18, 7);
        treasureMap.generate();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }
}
