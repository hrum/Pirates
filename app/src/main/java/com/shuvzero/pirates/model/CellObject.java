package com.shuvzero.pirates.model;

import com.shuvzero.pirates.R;

import java.util.Arrays;
import java.util.List;

public enum CellObject {

    Empty(R.drawable.empty, 100, Biome.Land, Biome.Ocean),
    Anchor(R.drawable.anchor, 100, Biome.Land, Biome.Ocean),
    Cactus(R.drawable.cactus, 100, Biome.Land),
    Cave(R.drawable.cave, 100, Biome.Land),
    Chest(R.drawable.chest, 100, Biome.Land),
    House(R.drawable.house, 100, Biome.Land),
    Lake(R.drawable.lake, 100, Biome.Land),
    Mark(R.drawable.mark, 100, Biome.Land),
    Mountain(R.drawable.mountain, 100, Biome.Land),
    Palm(R.drawable.palm, 100, Biome.Land),
    River(R.drawable.river,100, Biome.Land),
    Skull(R.drawable.skull,100, Biome.Land),
    Volcano(R.drawable.volcano, 100, Biome.Land);

    private int id;
    private int rarity;
    private List<Biome> biomes;

    CellObject(int id, int rarity, Biome... biomes) {
        this.id = id;
        this.rarity = rarity;
        this.biomes = Arrays.asList(biomes);
    }

    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }

    public boolean contains(Biome biome) {
        return biomes.contains(biome);
    }
}
