package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(5);
        treasureMap.fill();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }
}
