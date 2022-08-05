package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

public enum Biome {
    Ocean(R.drawable.ocean, 20),
    Land(R.drawable.land, 100);

    private int id;
    private int rarity;

    Biome(int id, int rarity) {
        this.id = id;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
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
