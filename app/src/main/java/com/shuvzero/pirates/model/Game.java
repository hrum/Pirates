package com.shuvzero.pirates.model;

import static com.shuvzero.pirates.view.MainMenuView.EASY_GAME;
import static com.shuvzero.pirates.view.MainMenuView.HARD_GAME;

public class Game {

    private TreasureMap treasureMap;
    private int difficulty;

    public Game(int difficulty) {
        this.difficulty = difficulty;
    }

    public void start() {
        treasureMap = new TreasureMap(15, 12);
        MapGenerator mapGenerator = new MapGenerator(treasureMap);
        mapGenerator.generate();
        treasureMap.setTreasureFeatures();
    }

    public TreasureMap getTreasureMap() {
        return treasureMap;
    }

    public boolean dig(int position) {
        int hint = 0;
        if(difficulty == EASY_GAME) {
            if(treasureMap.getTreasureFeatures().contains(treasureMap.getFeature(position)))
                hint = 1;
        } else if(difficulty == HARD_GAME) {
            for (Feature feature : treasureMap.getFeatures(position))
                if (treasureMap.getTreasureFeatures().contains(feature))
                    hint++;
        }
        treasureMap.getCell(position).setHint(hint);
        return position == treasureMap.getTreasurePosition();
    }

    public void erase(int position) {
        treasureMap.getCell(position).setHint(-1);
    }
}
