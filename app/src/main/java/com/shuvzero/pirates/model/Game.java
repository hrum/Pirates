package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(29, 11);
        treasureMap.generate();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }
}
