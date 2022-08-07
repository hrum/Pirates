package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Biome {
    Ocean(R.drawable.ocean, 30, false),
    Land(R.drawable.land, 100, true);

    private int id;
    private int rarity;
    private boolean isLand;

    Biome(int id, int rarity, boolean isLand) {
        this.id = id;
        this.rarity = rarity;
        this.isLand = isLand;
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }

    public boolean isLand() {
        return isLand;
    }

    public static Biome get(int num) {
        int sum = 0;
        for(Biome biome: Biome.values()) {
            sum += biome.rarity;
            if(num < sum)
                return biome;
        }
        return null;
    }

    public static int getTotalRarity() {
        int totalRarity = 0;
        for(Biome biome: Biome.values()) {
            totalRarity += biome.rarity;
        }
        return totalRarity;
    }
}
