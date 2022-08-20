package com.shuvzero.pirates.model;

public class Game {

    private TreasureMap treasureMap;

    public void start() {
        treasureMap = new TreasureMap(17, 14);
        MapGenerator mapGenerator = new MapGenerator(treasureMap);
        mapGenerator.generate();
        treasureMap.setTreasureFeatures();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }

    public boolean dig(int position) {
        int hint = 0;
        for(Feature feature: treasureMap.getFeatures(position))
            if(treasureMap.getTreasureFeatures().contains(feature))
                hint++;
        treasureMap.getCell(position).setHint(hint);
        return position == treasureMap.getTreasurePosition();
    }
}
